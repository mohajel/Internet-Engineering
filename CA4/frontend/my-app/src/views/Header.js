import React from 'react';

// navigate
import { useNavigate } from 'react-router-dom';

function Header(props) {
    let buttonText = props.buttonText;
    let navigate = useNavigate();

    function HandelClickHeaderButton() {
        navigate('/logout');
    }

    return (
        <header class="d-flex sticky-top container-fluid" id="header">
            <img src={require("../resources/images/logo.png")} alt="logo" class="logo" />
            <span class="header-text d-none d-sm-block"
            >Reserve Table From Anywhere!</span>
            {/* <button class="reserve-button rounded-3 border-0 ms-auto" onClick={HandelClickHeaderButton(buttonText)}> */}
                <button class="reserve-button rounded-3 border-0 ms-auto" onClick={HandelClickHeaderButton}>
                    Logout
                </button>
        </header>
    );
}



export default Header;