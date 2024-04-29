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

    const [xxxx, setPostId] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showError, setShowError] = useState(false);
    const [submit, setSubmit] = useState(false);

    const params = {
        username: "username",
        password: "password"
    };
    const options = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(params)
    };

    const handleClick = async (event) => {
        event.preventDefault();
        // setShowError(true);
        postReq('/testPost', { username: "username", password: "password" })
            .then(response => {
                setPostId(response);
                setShowError(true)
            });
    }

    return (
        <div>
            {showError ? (<MessagePage type='error' message="message" redirectURL='/login' />)
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
        <h1>salam <div><pre>{JSON.stringify(xxxx, null, 2)}</pre></div></h1>
    );
}


export default TestPost;

// function TestPost() {
//   const [errorMessage, setErrorMessage] = useState('');
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');
//   const [showError, setShowError] = useState(false);

//   const handleSubmit = async (event) => {
//     event.preventDefault();

//     try {
//       // Send POST request
//       const response = await fetch('/testPost', {
//         method: 'POST',
//         body: JSON.stringify({
//           username: username,
//           password: password
//         }),
//         headers: {
//           'Content-Type': 'application/json'
//         }
//       });

//       // Check response status
//       if (true) {
//         // Success
//         // Call a function or render a component for success
//         handleClick();
//       } else {
//         // Error
//         // Parse the error message from the response
//         const errorData = await response.json();
//         const error = errorData.message || 'An error occurred';

//         // Set the error message state
//         setErrorMessage(error);
//       }
//     } catch (error) {
//       // Network or server error
//       setErrorMessage('An error occurred');
//     }
//   };

//   const handleClick = () => {
//     setShowError(true);
//   };

//   return (
//     <div>
//       {showError ? (<MessagePage type='error' message='message' redirectURL='/login' />)
//         : (
//           <div>
//             <form onSubmit={handleClick}>
//               <label>
//                 Username:
//                 <input
//                   type="text"
//                   value={username}
//                   onChange={(event) => setUsername(event.target.value)}
//                 />
//               </label>
//               <br />
//               <label>
//                 Password:
//                 <input
//                   type="password"
//                   value={password}
//                   onChange={(event) => setPassword(event.target.value)}
//                 />
//               </label>
//               <br />
//               <button type="submit">Submit</button>
//             </form>
//           </div>
//         )}
//     </div>
//   );

//   return (
//     <div>
//       {errorMessage && <ErrorMessage message={errorMessage} />}
//       <form onSubmit={handleSubmit}>
//         <label>
//           Username:
//           <input
//             type="text"
//             value={username}
//             onChange={(event) => setUsername(event.target.value)}
//           />
//         </label>
//         <br />
//         <label>
//           Password:
//           <input
//             type="password"
//             value={password}
//             onChange={(event) => setPassword(event.target.value)}
//           />
//         </label>
//         <br />
//         <button type="submit">Submit</button>
//       </form>
//     </div>
//   );
// }

// function ErrorMessage({ message }) {
//   return <div>Error: {message}</div>;
// }

// function Error({ message }) {
//   return <div>Error: {message}</div>;
// }
