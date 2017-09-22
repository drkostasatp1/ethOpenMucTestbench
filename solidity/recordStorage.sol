pragma solidity ^0.4.1;
/*******************************************************************************************
 * A Simple CRUD Storage contract to enable the storage of Strings with user persmission management
  * based on the tutorial from Rob Hitchens, Solidity CRUD. (Url insertion not possible here, solc does not compile the file in that case)
 *******************************************************************************************/
  
contract recordStorage {
/*******************************************************************************************
 * Define Types 
 *******************************************************************************************/
   struct recordStruct {
    string data;
    uint index;
}
 struct userPermissions{
    bool write;
    bool read;
}
struct RanngeCheckValues{
    uint high;
    uint low;
}

 /*******************************************************************************************
 * Define Global Variables 
    owner and creationTime (blocktime) will be set upon contract creation
 *******************************************************************************************/
 	string public version = "1.4";
	address public owner = msg.sender;
    uint public creationTime = now;
    RanngeCheckValues private rangeCheckValues;
    
  // Address is a 20 byte identifier (160 Bits)
  // this is the main structure holding the data. Comparable to a hash table, it contains a mapping from a address to a string (key value)
    
     mapping(address => recordStruct) private stringStructs;
  // Store the user permissions:
     mapping(address => userPermissions) private theUserPermissions;
  // Store an array of addresses to provide iteration capabilities
     address[] private stringIndex;
     
/*******************************************************************************************
* Function Modifyers. These wrap Functions and execute them based on Conditions. 
 *******************************************************************************************/
 modifier onlyBy(address _account)
    {
        require(msg.sender == _account);
        // Do not forget the "_;"! It will
        // be replaced by the actual function
        // body when the modifier is used.
        _;
    }
  modifier requireRead()
  {
   require (theUserPermissions[msg.sender].read);
    _;
  }
  modifier requireWrite()
  {
   require (theUserPermissions[msg.sender].read);
    _;
  }
     
     
/*******************************************************************************************
 * Define Events 
 *******************************************************************************************/
     
  event LogNewString   (address indexed userAddress, uint index,string data);
  event LogUpdateString(address indexed userAddress, uint index,string data);
  event LogDeleteString(address indexed userAddress, uint index);
  // These events could be used to trigger updates on the remote side. Web3j can be used to observe these events
  event notifyAll();
  event notify(string id);
  event outOfBounds(address userAddress, uint value);
  
    
/*******************************************************************************************
 * This is the contract Constructor that is executed once the contract is created
 * The method will store the creator of the contract to enable user permissions management 
 * msg.sender is available to all functions 
    *******************************************************************************************/
 function recordStorage()
 public
 {
     // Set the permission for the owner
     setPermissionWrite(owner, true);
     setPermissionRead(owner, true);
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
    public
    returns(uint index)
{
     require (theUserPermissions[msg.sender].write);
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
  
/**
 * Following are functions to set and reset the permissions of users. 
 */
function getPermissionRead(address adr) 
public 
constant
returns (bool permission)
{
	return theUserPermissions[adr].read;
}
function getPermissionWrite(address adr) 
public 
constant
returns (bool permission)
{
	return theUserPermissions[adr].write;
}
function setPermissionRead(address user, bool perm)  onlyBy(owner) public
{
    theUserPermissions[user].read = perm;
}
function setPermissionWrite(address user, bool perm)  onlyBy(owner) public
{
    theUserPermissions[user].write = perm;
}

 /*******************************************************************************************
   *	Functions to perform the simple range checking functionality 
   *******************************************************************************************/

function isInRange(uint value) requireRead() public constant returns (bool inRange)
{
    if ( (value < rangeCheckValues.low) || (value>rangeCheckValues.high)  )
    {
        // disable logging to enable simple calling of the contract
       outOfBounds(msg.sender,value);
       return false;
      }
      else return true;
}
function getBoundedValue (uint value) requireRead() public constant returns (uint retVal)
{
    if (isInRange(value)) return value;
    else
    if (value < rangeCheckValues.low) 
        return rangeCheckValues.low;
    else 
        return rangeCheckValues.high;
}

 function getHighValue() requireRead()
    public
    constant
    returns(uint highValue)
  {
      return rangeCheckValues.high;
  }
 function getLowValue() requireRead()
    public
    constant
    returns(uint lowValue)
  {
      return rangeCheckValues.low;
  }
  
function setRangeValues(uint high, uint low)  onlyBy(owner) public
{
    rangeCheckValues.high = high;
    rangeCheckValues.low = low;
}

}// end contract
