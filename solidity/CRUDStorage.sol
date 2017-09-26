pragma solidity ^0.4.16;
/*******************************************************************************************
 * A Simple CRUD Storage contract to enable the storage of Strings with user persmission management
  * based on the tutorial from Rob Hitchens, Solidity CRUD. (Url insertion not possible here, solc does not compile the file in that case)
 *******************************************************************************************/
// Make the contract mortal and ownable, and inherit user permissions modifiers
import "./userPermissions.sol";
import "./mortal.sol";
import "./versionable.sol";

 contract CRUDStorage is mortal, userPermissions, versionable {        
/*******************************************************************************************
 * Define Types 
 *******************************************************************************************/
   struct recordStruct {
    string data;
    uint index;
}

 /*******************************************************************************************
 * Define Global Variables 
    owner and creationTime (blocktime) will be set upon contract creation
 *******************************************************************************************/
  // Address is a 20 byte identifier (160 Bits)
  // this is the main structure holding the data. Comparable to a hash table, it contains a mapping from a address to a string (key value)
     mapping(address => recordStruct) private stringStructs;
   // Store an array of addresses to provide iteration capabilities
     address[] private stringIndex;

/*******************************************************************************************
 * Define Events 
 *******************************************************************************************/
     
  event LogNewString   (address indexed userAddress, uint index,string data);
  event LogUpdateString(address indexed userAddress, uint index,string data);
  event LogDeleteString(address indexed userAddress, uint index);
  // These events could be used to trigger updates on the remote side. Web3j can be used to observe these events
  event notifyAll();
  event notify(string id);
  event outOfBounds(address userAddress, int value);
  
    
/*******************************************************************************************
 * Constructor
 * This is the contract Constructor that is executed once the contract is created
 * The method will store the creator of the contract to enable user permissions management 
 * msg.sender is available to all functions 
    *******************************************************************************************/
 function CRUDStorage()
 public
 {
     setVersion("1.6-with inheritannce");
     setDescription("Simple CRUD Contract with user permissions");
 }
  /*******************************************************************************************
   *    This function returns a boolean value, True if the given key is already registered 
   *    Available publicly
   *******************************************************************************************/
  function contains(address userAddress)
    public 
    constant
    returns(bool isIndeed) 
  {
    if(stringIndex.length == 0) return false;
    return (stringIndex[stringStructs[userAddress].index] == userAddress);
  }
/*******************************************************************************************
* Insert a String into the Contract. Will only succed if the address in not taken already. 
* Available Public, requires write permission.
*******************************************************************************************/
  function setString(
    address userAddress, 
    string data) 
    requireWrite()
    public
    returns(uint index)
{
     require(!contains(userAddress));
    //if(contains(userAddress)) throw;
    stringStructs[userAddress].data = data;
    stringStructs[userAddress].index = stringIndex.push(userAddress)-1;
    LogNewString(
        userAddress, 
        stringStructs[userAddress].index, 
        data);
    return stringIndex.length-1;
}
   /*******************************************************************************************
   *    This Function will Delete a String from the contract. It will not free space in the 
   *	blockchain (that is impossible), but the data structure so that the item is unavailable. 
   *    Available publicly
   *******************************************************************************************/
   function deleteString(address userAddress) requireWrite()
  	public
    returns(uint index)
  {
    require(contains(userAddress));
    uint rowToDelete = stringStructs[userAddress].index;
    address keyToMove = stringIndex[stringIndex.length-1];
    stringIndex[rowToDelete] = keyToMove;
    stringStructs[keyToMove].index = rowToDelete; 
    stringIndex.length--;
    LogDeleteString(
        userAddress, 
        rowToDelete);
    LogUpdateString(
        keyToMove, 
        rowToDelete, 
        stringStructs[keyToMove].data);
    return rowToDelete;
  }
  /*******************************************************************************************
   *	Returns a String that is stored at a specific address. 
   *    Available publicly
   *******************************************************************************************/
  function getString(address userAddress) requireRead()
    public 
    constant
    returns(string userData)
  {
    require(contains(userAddress));
    return stringStructs[userAddress].data;
    } 
  
  /*******************************************************************************************
  *	Updates a String that is stored at a specific address. 
  *    Available publicly
  *******************************************************************************************/
    
function updateString(address userAddress, string userData)  requireWrite()
    public
    returns(bool success) 
  {
    require(contains(userAddress));
    stringStructs[userAddress].data = userData;
   
    LogUpdateString(
      userAddress, 
      stringStructs[userAddress].index,
      stringStructs[userAddress].data);
    return true;
  }
       
 /*******************************************************************************************
 *	Returns the number of records stored in the contract 
 *    Available publicly
 *******************************************************************************************/
  function getCount() requireRead()
    public
    constant
    returns(uint count)
  {
      return stringIndex.length;
  }
 /*******************************************************************************************
 *	Returns the address of a string a specific index. Order is based on the order of storage. 
 *    Available publicly
 *******************************************************************************************/
  function getStringAtIndex(uint index) requireRead()
    public
    constant
    returns(address userAddress)
  {
      return stringIndex[index];
  }
 }// end contract
