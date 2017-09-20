#!/bin/bash 
# This will compile all solidity contracts in their directory to a build dir, then auto create wrapper classes
# GLOBAL VARIABLES
BUILD=/Volumes/UserData/mposch/Documents/FH/MasterThesis_MatthiasPosch/java/MT_Experiment4/solidity/build/
PKG=org.mposch.solidityContractWrapper
SRC=/Volumes/UserData/mposch/Documents/FH/MasterThesis_MatthiasPosch/java/MT_Experiment4/src/main/java/
SOL=/Volumes/UserData/mposch/Documents/FH/MasterThesis_MatthiasPosch/java/MT_Experiment4/solidity/

#First compile all contracts
mkdir -p $BUILD
cd $SOL
make all

# All contracts compiled and abi+bin in the build dir
cd $BUILD
# now generate the wrappers
for file in *.bin; do
n=`basename -s .bin $file`
	web3j solidity generate $n.bin $n.abi -o $SRC -p $PKG
done;
