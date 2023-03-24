# IS6200_group_project

## Introduction

{**CHAIN group project documents**}

## Note

1. before running this application, You are required to initialize the fabric network first.
2. you are required to initialize the network with the following command:
   `./network.sh up createChannel -c mychannel -ca`
   The command will build a channel, and then create a CA to define the admin.
3. If the wallet dictionary is existed in the project, please remove it or your application will get error after network
   restart.
4. you are required to pull the project in the `~/fabric-samples/asset-transfer-basic/`, then you can run this
   application.