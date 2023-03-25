package bao;

import model.Asset;
import org.hyperledger.fabric.client.*;

public class modify {

    private final Contract contract;

    public modify(final Gateway gateway, String CHANNEL_NAME, String CHAINCODE_NAME) {
        // Get a network instance representing the channel where the smart contract is
        // deployed.
        var network = gateway.getNetwork(CHANNEL_NAME);

        // Get the smart contract from the network.
        contract = network.getContract(CHAINCODE_NAME);
    }

    public void updateExistAsset(Asset asset, String newJob, String newDepartment, String newSalary, String newRecord) {
        System.out.println("\n--> Submit Transaction: UpdateAsset Em11923, Em11923 should be updated with given parameters");

        try {
            contract.submitTransaction("ChangeJob", asset.getEmployeeID(), newJob, newDepartment, newSalary, newRecord);
        } catch (EndorseException | SubmitException | CommitStatusException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

}
