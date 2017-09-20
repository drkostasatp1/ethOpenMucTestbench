####################################
# Makefile to compile Solidity 
# Contracts, and create Java 
# Files
####################################


####################################
# GLOBAL VARIABLES
####################################

# Insert the Base Directory of the Project here:
BASEDIR=/Volumes/UserData/mposch/Documents/FH/MasterThesis_MatthiasPosch/java/MT_Experiment4/
# This is the directory where all solidity binaries will be placed:
BUILD=$(BASEDIR)solidity/build/
#The package name that will be applied to the auto created wrappers:
PKG=org.mposch.solidityContractWrapper
#This is the actual directories location where the Java files will be placed
JAVADIR=$(BASEDIR)src/main/java/
#The location where the solidity sources are located:
SOL=$(BASEDIR)solidity/

CC=solc
CFLAGS=--bin --abi --optimize --overwrite -o $(BUILD)

####################################
# Default action is to create java code. 
####################################

all: createWrappers


# As a final simplification, let's use the special macros $@ and $^, which are the left and 
# right sides of the :, respectively, to make the overall compilation rule more general. 

SRC=$(wildcard *.sol)

####################################
# Create Binary Files 
####################################
# This will create the Solidity binaries in the Build directory (.bin and .abi files):
bin: $(SRC)
	$(CC) $^ $(CFLAGS)
.PHONY: clean
.PHONY: createWrappers

# Use the web3j framework to auto create Java code. Iterate over all bin files and create the Java code
####################################
# Create Java Files 
####################################
createWrappers: bin
	cd $(BUILD) ; \
	for f in $(BUILD)*.bin ; do \
   	 n=`basename -s .bin $$f` ; \
   	 echo $$n ; \
   	 web3j solidity generate $$n.bin $$n.abi -o $(JAVADIR) -p $(PKG) ;\
	done
clean:
	rm -f $(BUILD)*.bin; rm -f $(BUILD)*.abi; 
