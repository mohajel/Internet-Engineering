import React from 'react';
import { useEffect, useState } from 'react';

import { postReq, getReq } from '../utils/api';

const MessagePage = React.lazy(() => import('./MessagePage'));

// function TestPost() {
//   const [showError, setShowError] = useState(false);

//   const handleClick = () => {
//     setShowError(true);
//   };

//   return (
//     <div>
//       {showError ? (
//         <MessagePage type='error' message='message' redirectURL='/login' />
//       ) : (
//         <button onClick={handleClick}>Show Error</button>
//       )}
//     </div>
//   );
// }

function TestPost() {

    const [loginData, setLoginData] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showError, setShowError] = useState(false);

    const handleClick = async (event) => {
        event.preventDefault();
        postReq('/login', { username: username, password: password })
            .then(response => {
                setLoginData(response);
                setShowError(true)
            });
    }

    return (
        <div>
            {showError ? (<MessagePage type={loginData.success == true ? "info" : "error"}
                message={loginData.success == true ? "Welcome to Mizdooni" : loginData.data.error} redirectURL="/" />)
                : (
                    <div>
                        salammmmm
                        <form onSubmit={handleClick}>
                            <label>
                                Username:
                                <input
                                    type="text"
                                    value={username}
                                    onChange={(event) => setUsername(event.target.value)}
                                />
                            </label>
                            <br />
                            <label>
                                Password:
                                <input
                                    type="password"
                                    value={password}
                                    onChange={(event) => setPassword(event.target.value)}
                                />
                            </label>
                            <br />
                            <button type="submit">Submit</button>
                        </form>
                    </div>
                )}
        </div>
    );


    return (
        <h1>salam <div><pre>{JSON.stringify(loginData, null, 2)}</pre></div></h1>
    );
}


export default TestPost;




// function TestPost() {

//     const [loginData, setLoginData] = useState(null);
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const [showError, setShowError] = useState(false);

//     const handleClick = async (event) => {
//         event.preventDefault();
//         postReq('/login', { username: username, password: password })
//             .then(response => {
//                 setLoginData(response);
//                 setShowError(true)
//             });
//     }

//     return (
//         <div>
//             {showError ? (<MessagePage type={loginData.success == true ? "info" : "error"}
//                 message={loginData.success == true ? "Welcome to Mizdooni" : loginData.data.error} redirectURL="/" />)
//                 : (
//                     <div>
//                         salammmmm
//                         <form onSubmit={handleClick}>
//                             <label>
//                                 Username:
//                                 <input
//                                     type="text"
//                                     value={username}
//                                     onChange={(event) => setUsername(event.target.value)}
//                                 />
//                             </label>
//                             <br />
//                             <label>
//                                 Password:
//                                 <input
//                                     type="password"
//                                     value={password}
//                                     onChange={(event) => setPassword(event.target.value)}
//                                 />
//                             </label>
//                             <br />
//                             <button type="submit">Submit</button>
//                         </form>
//                     </div>
//                 )}
//         </div>
//     );


//     return (
//         <h1>salam <div><pre>{JSON.stringify(loginData, null, 2)}</pre></div></h1>
//     );
// }


// export default TestPost;