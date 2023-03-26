# IS6200_group_project

## Introduction

{**CHAIN group project documents**}

## Note

1. before running this application, You are required to initialize the fabric network first.
2. you are required to pull the project in the `~/fabric-samples/asset-transfer-basic/`, then you can run this
   application.
3. you are required to initialize the network with the following command:
   `./network.sh up createChannel -c mychannel -ca`
   The command will build a channel, and then create a CA to define the admin.
4. use `./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-java -ccl java` to deploy your
   contract. You need the modified version
5. After that, run the `initial.java` firstly.