pragma solidity ^0.4.16;

contract uintSimpleStorage {
    uint storedData;
  function set(uint x) {
        storedData = x;
    }
  function get() constant returns (uint retVal) {
        return storedData;
    }
}