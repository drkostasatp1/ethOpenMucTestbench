# Installing the EthTestbench on a Windows machine, step by step
Requirements: Java JRE, Git, OpenMuc

## Install a Java JRE
 
Your system must be configured to host a Java JRE Environment. 
 
## Install the Ethereum Wallet
Download the Ethereum Wallet from https://github.com/ethereum/mist/releases

The version used wihin this guide is 0.9.3, available from https://github.com/ethereum/mist/releases/download/v0.9.3/Ethereum-Wallet-installer-0-9-3.exe

1) Run the Ethereum Installer, run the application. 

To use one of the testnets choose a Testnet, in this guide i choose the ropsten testnetwork. 
Let the Blockchain sync for a while, this can take quite some time. 

While the Blockchain syncs, install the other requirements

## Install Openmuc

Download the latest Version of Openmuc from https://www.openmuc.org/openmuc/download/
In this guide i will use version 0.16.0 , available at [here] (https://www.openmuc.org/openmuc/files/releases/openmuc-0.16.0.tgz?t=1513112571221)

2) Unpack the downloaded archive, and use a terminal session to launch the openmuc demo using 
```
bin\openmuc.bat
```
as described in the OpenMuc [quick start guide](https://www.openmuc.org/openmuc/user-guide/#_quick_start). 
This will start a the OSGI framework (Apache Felix) and launch the demo project, which can be interfaced by this project.
### Verify Openmuc
It is easy to verify that openMuc is running by visiting the webpage that will be available (http://localhost:8888)


## Install Git

I would recommend to use git to checkout the latest version of the repository. To install git for windows just download the latest version at (http://msysgit.github.io)

And then use the clone command in a terminal window to download the Project. the project will be downloaded wherever to the current directoriy of the shell. 
```
git clone https://github.com/mposch/ethOpenMucTestbench.git
```
If you do not want to install git, you can just download the master zip file from github: https://github.com/mposch/ethOpenMucTestbench/archive/master.zip

To actually run the software just type
```
cd ethOpenMucTestbence
gradlew.bat bootRun
```

