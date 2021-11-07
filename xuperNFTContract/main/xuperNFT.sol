// SPDX-License-Identifier: SimPL-2.0
pragma solidity >=0.0.0;

import "../contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "../contracts/utils/Counters.sol";
contract xuperNFT is ERC721URIStorage {

    using Counters for Counters.Counter;
    Counters.Counter private _tokenIdTracker;

    constructor(
        string memory name_,
        string memory symbol_
    ) 
            ERC721(name_, symbol_) 
    { 
        
    }

    function mint(
        address to,
        string memory tokenURI
    ) public {
        _setTokenURI(_tokenIdTracker.current(),tokenURI);
        _mint(to,_tokenIdTracker.current());
        _tokenIdTracker.increment();
    }
}