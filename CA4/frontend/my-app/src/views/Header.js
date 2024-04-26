import React from 'react';

function Header() {
    return (
        <header class="d-flex sticky-top container-fluid" id="header">
            <img src={require("../resources/images/logo.png")} alt="logo" class="logo" />
            <span class="header-text d-none d-sm-block"
            >Reserve Table From Anywhere!</span>
            <button class="reserve-button rounded-3 border-0 ms-auto">
                Reserve Now!
            </button>
        </header>
    );
}

export default Header;