package bao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.GatewayException;

import java.nio.charset.StandardCharsets;

public class inquery {

    private final Contract contract;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public inquery(final Gateway gateway, String CHANNEL_NAME, String CHAINCODE_NAME) {
        // Get a network instance representing the channel where the smart contract is
        // deployed.
        var network = gateway.getNetwork(CHANNEL_NAME);

        // Get the smart contract from the network.
        contract = network.getContract(CHAINCODE_NAME);
    }


    private String prettyJson(final byte[] json) {
        return prettyJson(new String(json, StandardCharsets.UTF_8));
    }

    private String prettyJson(final String json) {
        var parsedJson = JsonParser.parseString(json);
        return gson.toJson(parsedJson);
    }

    public String getAllAssets() throws GatewayException {
        System.out.println("\n--> Evaluate Transaction: GetAllAssets, function returns all the current assets on the ledger");

        var result = contract.evaluateTransaction("GetAllAssets");

        return prettyJson(result);
    }

    public String getAsset(String employeeID) throws GatewayException {
        System.out.println("\n--> Evaluate Transaction: ReadAsset, function returns asset attributes");

        var evaluateResult = contract.evaluateTransaction("ReadAsset", employeeID);

        return prettyJson(evaluateResult);
    }
}
