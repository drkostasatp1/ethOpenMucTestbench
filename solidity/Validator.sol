pragma solidity ^0.4.16;

// Interface to describe the Validator Interface

contract Validator{
    function isValid(int value)  public constant returns (bool retVal);
    function getBoundedValue(int inValue) public constant returns (int boundedVal);
}