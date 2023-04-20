# IS6200 CHAIN Group Project

## Introduction
![CHAIN_logo](https://github.com/AvalonC/IS6200_CHAIN_group_project/blob/main/CHAIN.png)

**CHAIN Group Project**

**Blockchain_based Employee Information System(BEIS)**

## Prepare for Running the system

Given we still need run a java file before the system run, I suggest you to run this application in the IDE.

If you are using Windows 10/11 with WSL 2(It can only run on WSL 2), You need the following software:
- HyperLedger Fabric Sample
- IntelliJ IDEA (2021+)
  - Using the remote development function to connect the folder in which you pull this project.
- Docker Desktop (Already connected with WSL2)
- WSL 2 (Ubuntu 22.04)
- Tomcat 8.4 in WSL 2
- Java 11 in WSL 2

If you are using Linux, You only need 
- HyperLedger Fabric Sample
- IntelliJ IDEA
- Docker
- Tomcat 8.4
- Java 11

## Steps

1. before running this application, You are required to prepare all the softwares first.
2. you are required to pull the project in the `~/fabric-samples/asset-transfer-basic/`, then you can run this application.
3. you are required to initialize the network with the following command:   `./network.sh up createChannel -c mychannel -ca` 
The command will build a channel, and then create a CA to define the admin.
4. Deploy the chaincode. You need the modified chaicode from the link: https://github.com/AvalonC/IS6200_CHAIN_group_project_chaincode
You must finished the deployment so that you can start step 5.
5. After that, run the `initial.java` firstly.

## Before you runing....

You need to modify several codes in the viewModel, find the `viewModel` package under `src/main/java` folder.
and modified the `CRYPTO_PATH` parameter in each class as 

`<path start from root>/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com`

_i.e., we need the absolute path of the `org1.example.com`, you will see `/home/avalonc` in the code, which is incorrect for your machine, you need to modify them_

_p.s. The relative path cannot work properly in the Tomcat service; therefore, I can use only the absolute path. I have no time to write a configuration document for this project to make it more OOP._

