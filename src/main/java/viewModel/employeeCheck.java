package viewModel;

import bao.inquery;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import model.Asset;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.identity.*;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

public class employeeCheck implements Serializable {
    private static final String MSP_ID = System.getenv().getOrDefault("MSP_ID", "Org1MSP");
    private static final String CHANNEL_NAME = System.getenv().getOrDefault("CHANNEL_NAME", "mychannel");
    private static final String CHAINCODE_NAME = System.getenv().getOrDefault("CHAINCODE_NAME", "basic");

    // Path to crypto materials.
    private static final Path CRYPTO_PATH = Paths.get("/home/avalonc/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com");
    // Path to user certificate.
    private static final Path CERT_PATH = CRYPTO_PATH.resolve(Paths.get("users/User1@org1.example.com/msp/signcerts/cert.pem"));
    // Path to user private key directory.
    private static final Path KEY_DIR_PATH = CRYPTO_PATH.resolve(Paths.get("users/User1@org1.example.com/msp/keystore"));
    // Path to peer tls certificate.
    private static final Path TLS_CERT_PATH = CRYPTO_PATH.resolve(Paths.get("peers/peer0.org1.example.com/tls/ca.crt"));

    // Gateway peer end point.
    private static final String PEER_ENDPOINT = "localhost:7051";
    private static final String OVERRIDE_AUTH = "peer0.org1.example.com";

    private String checkInfo = "";
    private String checkResult = "";

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    private static ManagedChannel newGrpcConnection() throws IOException, CertificateException {
        var tlsCertReader = Files.newBufferedReader(TLS_CERT_PATH);
        var tlsCert = Identities.readX509Certificate(tlsCertReader);

        return NettyChannelBuilder.forTarget(PEER_ENDPOINT)
                .sslContext(GrpcSslContexts.forClient().trustManager(tlsCert).build()).overrideAuthority(OVERRIDE_AUTH)
                .build();
    }

    private static Identity newIdentity() throws IOException, CertificateException {
        var certReader = Files.newBufferedReader(CERT_PATH);
        var certificate = Identities.readX509Certificate(certReader);

        return new X509Identity(MSP_ID, certificate);
    }

    private static Signer newSigner() throws IOException, InvalidKeyException {
        var keyReader = Files.newBufferedReader(getPrivateKeyPath());
        var privateKey = Identities.readPrivateKey(keyReader);

        return Signers.newPrivateKeySigner(privateKey);
    }

    private static Path getPrivateKeyPath() throws IOException {
        try (var keyFiles = Files.list(KEY_DIR_PATH)) {
            return keyFiles.findFirst().orElseThrow();
        }
    }

    public void check() throws CertificateException, IOException, InvalidKeyException {
        checkInfo = getCheckInfo();
        var channel = newGrpcConnection();

        var builder = Gateway.newInstance().identity(newIdentity()).signer(newSigner()).connection(channel)
                // Default timeouts for different gRPC calls
                .evaluateOptions(options -> options.withDeadlineAfter(5, TimeUnit.SECONDS))
                .endorseOptions(options -> options.withDeadlineAfter(15, TimeUnit.SECONDS))
                .submitOptions(options -> options.withDeadlineAfter(5, TimeUnit.SECONDS))
                .commitStatusOptions(options -> options.withDeadlineAfter(1, TimeUnit.MINUTES));

        try (var gateway = builder.connect()) {

            JSONObject employee = JSON.parseObject(checkInfo);
            Asset asset = new Asset();
            asset.setBirthday(employee.getString("birthday"));
            asset.setCardNumber(employee.getString("cardNumber"));
            asset.setDepartment(employee.getString("department"));
            asset.setGraduateSchool(employee.getString("graduateSchool"));
            asset.setJobTitle(employee.getString("jobTitle"));
            asset.setName(employee.getString("name"));
            asset.setRecord(employee.getString("record"));
            asset.setSalary(Float.parseFloat(employee.getString("salary")));

            asset.setEmployeeID(employee.getString("employeeID"));
            String existEmployee = new inquery(gateway, CHANNEL_NAME, CHAINCODE_NAME).getAsset(employee.getString("employeeID"));
            JSONObject existEmployeeInfo = JSON.parseObject(existEmployee);
            Asset existAsset = new Asset();
            existAsset.setBirthday(existEmployeeInfo.getString("birthday"));
            existAsset.setCardNumber(existEmployeeInfo.getString("cardNumber"));
            existAsset.setDepartment(existEmployeeInfo.getString("department"));
            existAsset.setEmployeeID(existEmployeeInfo.getString("employeeID"));
            existAsset.setGraduateSchool(existEmployeeInfo.getString("graduateSchool"));
            existAsset.setJobTitle(existEmployeeInfo.getString("jobTitle"));
            existAsset.setName(existEmployeeInfo.getString("name"));
            existAsset.setRecord(existEmployeeInfo.getString("record"));
            existAsset.setSalary(Float.parseFloat(existEmployeeInfo.getString("salary")));
            if (asset.equals(existAsset)) {
                checkResult = "This employee is existed, the information presented below:<br/>" + existAsset;
            }
        } catch (Exception e) {
            checkResult = "This is an invalid information!!";
        }
    }
}
