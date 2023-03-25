package bao;

import model.Asset;
import org.hyperledger.fabric.client.*;

public class insert {

    private final Contract contract;

    public insert(final Gateway gateway, String CHANNEL_NAME, String CHAINCODE_NAME) {
        // Get a network instance representing the channel where the smart contract is
        // deployed.
        var network = gateway.getNetwork(CHANNEL_NAME);

        // Get the smart contract from the network.
        contract = network.getContract(CHAINCODE_NAME);
    }

    public void createAsset(Asset asset) throws EndorseException, SubmitException, CommitStatusException, CommitException {
        System.out.println("\n--> Submit Transaction: CreateAsset, creates new asset with new arguments");

        contract.submitTransaction("CreateAsset", asset.getEmployeeID(), asset.getName(), asset.getBirthday(),
                asset.getBirthday(), asset.getJobTitle(), asset.getDepartment(), String.valueOf(asset.getSalary()),
                asset.getCardNumber(), asset.getRecord());

        System.out.println("*** Transaction committed successfully");
    }
}
