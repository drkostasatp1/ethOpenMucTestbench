# Installing the EthTestbench on a Windows machine, step by step Quick Start Guide
Requirements: Java JRE, (Git), OpenMuc

## Install a Java JRE
 
Your system must be configured to host a Java JRE Environment. 
 


## Install Geth for Windows

Download the installer at https://geth.ethereum.org/downloads/
I this guide i used version 1.7.3

Once the installer is finished , open a command window. The first step is to create an account using the geth commandline tool:
```
geth --testnet account new
```
The programm will ask for a password (which is used to encrypt the provate keys which are stored in the keyfile). Afterwards the ethereum blockchain can be executed again using following options
```
geth --testnet --mine --rpc --syncmode "light"
```
This will start the Ethereum blockchain node, with mining enabled in order to be able to perform transactions. The sync process will take a while


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
cd ethOpenMucTestbench
gradlew.bat bootRun
```


