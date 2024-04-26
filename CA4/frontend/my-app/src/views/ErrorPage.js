import React from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';


function ErrorPage() {
  const navigate = useNavigate();

  var notification = {
    type: 'error',
    message: 'An error occurred. Redirecting to home page...',
    redirectURL: '/'
  };

  let timerInterval;
  Swal.fire({
      title: notification.type,
      text: notification.message,
      icon: notification.type,
      timer: 2000,
      timerProgressBar: true,
      didOpen: () => {
          Swal.showLoading();
      },
      willClose: () => {
          clearInterval(timerInterval);
      }
  }).then((result) => {

      if (result.dismiss === Swal.DismissReason.timer) {
      console.log("redirecting to " + notification.redirectURL);
      navigate('/');
    //   window.location.href = notification.redirectURL;
      }
  });

  return (
    <div>
      <h1>My Page</h1>
      <p>Redirecting in 3 seconds...</p>
      <button>Redirect Now</button>
    </div>
  );
}

export default ErrorPage;