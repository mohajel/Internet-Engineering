import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

import "../resources/styles/home_page.css"

// images
import StarEmpty from "../resources/images/icons/star_empty.svg";
import StarFilled from "../resources/images/icons/star_filled.svg";
import LocationIcon from "../resources/images/icons/location.svg";

import Header from './Header';

function HomePage() {


        const [posts, setPosts] = useState([]);
        useEffect(() => {
           fetch('/api')
              .then((response) => response.json())
              .then((data) => {
                 console.log(data);
                 setPosts(data);
              })
              .catch((err) => {
                 console.log(err.message);
              });
        }, []);
     
     return (
        <h1>salam {posts.title}</h1>
     );


    }



//     // const [photos, setPhotos] = useState([]);
//     // useEffect(() => {
//     //   fetch("./api")
//     //     .then((res) => {
//     //       return res.json();
//     //     })
//     //     .then((data) => {
//     //       console.log(data);
//     //       setPhotos(data);
//     //     });
//     // }, []);
//     // return (
//     //   <div>
//     //     sss
//     //     {photos}
        
//     //     {/* {photos.map((photo) => (
//     //       <img key={photo.id} src={photo.url} alt={photo.title} width={100} /> */}
//     //     {/* ))} */}
//     //   </div>
//     // );

//     // const navigate = useNavigate();

//     // const [isLoggedIn, setIsLoggedIn] = useState(false);
//     // const [isLoading, setIsLoading] = useState(true);
//     // const [userStatus, setUserStatus] = useState(null);

//     // useEffect(() => {
//     // Make an API call to check if the user is logged in
//     // Assuming the API endpoint returns a boolean value indicating the login status
//     //   fetch('/api')
//     //     .then(response => response.json())
//     //     .then(data => {
//     //       setIsLoggedIn(data.isLoggedIn);
//     //     //   setIsLoading(false);
//     //       setUserStatus(data);
//     //       console.log('User status:', data);
//     //     })
//     //     .catch(error => {
//     //       console.error('Error checking login status:', error);
//     //     //   setIsLoading(false);
//     //     });
//     // }, []);


//     // const [data, setData] = React.useState(null);

//     // React.useEffect(() => {
//     //     fetch("/userStatus")
//     //         .then((res) => res.json())
//     //         // .then((data) => setData(data.message));
//     //         .then((data) => setUserStatus(data.role));
//     // }, []);



//     // if (isLoading) {
//     //     return <div>data is:: {userStatus}</div>;
//     // }



//     return (
//         <div class="d-flex flex-column">

//             <Header />

//             <main class="flex-grow-1">
//                 <div class="container-s w-100 text-center">
//                     <div class="home-background w-100">
//                         <div class="container">
//                             <div class="input-group mb-3">
//                                 <img class="big-logo" src={require("../resources/images/logo.png")} alt="logo" />
//                             </div>
//                             <div class="input-group mb-1 text-center">
//                                 <select class="custom-select rounded-4 search-input" id="inputGroupSelect01" aria-placeholder="Location">
//                                     <option selected>Location</option>
//                                     <option value="ff">Tehran</option>
//                                     <option value="tf">Rasht</option>
//                                     <option value="if">Gonbad</option>
//                                 </select>
//                                 <select class="custom-select rounded-4 search-input" id="RestaurantType">
//                                     <option selected>Restaurant</option>
//                                     <option value="ff">Fast Food</option>
//                                     <option value="tf">Traditional Food</option>
//                                     <option value="if">Italian Food</option>
//                                 </select>
//                                 <input type="text" class="search-input restaurant-search rounded-4" placeholder="Type Restaurant ..."
//                                     aria-label="restaurant search" aria-describedby="basic-addon2" />
//                                 <button class="btn search-input btn-outline-secondary rounded-4 search-button" type="button">
//                                     Search
//                                 </button>
//                             </div>
//                         </div>
//                     </div>
//                 </div>

//                 <div class="p-3 container">
//                     <div class="p-3 container" id="suggestions-container">
//                         <div id="top-restaurants-id">
//                             <div class="result-title">Top restaurants in Mizdooni</div>
//                         </div>

//                         <div id="seggested-top-restaurants-id">
//                             <div
//                                 class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarEmpty} alt="star_empty" />
//                                                 <img class="icon" src={StarEmpty} alt="star_empty" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant1.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest1_picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">The Ivy Brasserie</div>
//                                             <div class="review-count card-text">2096 reviews</div>
//                                             <div class="type card-text">Contemporary British</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Tehran
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="open">Open</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Closes at 11 PM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_empty" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant2.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">Haida Restaurant</div>
//                                             <div class="review-count card-text">12096 reviews</div>
//                                             <div class="type card-text">Fast Food</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Tehran
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="closed">Closed</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Opens at 10 AM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_empty" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant3.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest3_picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">Eline Cafe</div>
//                                             <div class="review-count card-text">0 reviews</div>
//                                             <div class="type card-text">Sea Food</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Rasht
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="open">Open</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Closes at 12 PM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>

//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_empty" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant4.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest4-picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">Cecconi's</div>
//                                             <div class="review-count card-text">2096 reviews</div>
//                                             <div class="type card-text">Healthy Food</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Ahvaz
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="open">Open</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Closes at 11 PM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_empty" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant5.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest5-picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">Hard Rock Cafe</div>
//                                             <div class="review-count card-text">2096 reviews</div>
//                                             <div class="type card-text">Vegetarian Food</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Tabriz
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="open">Open</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Closes at 11 PM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                                 <div class="col">
//                                     <div class="restaurant card rounded-4 h-100 position-relative">
//                                         <a href="#" class="restaurant-link">
//                                             <div class="rate d-flex position-absolute px-2 py-1">
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                                 <img class="icon" src={StarFilled} alt="star_empty" />
//                                                 <img class="icon" src={StarFilled} alt="star_filled" />
//                                             </div>
//                                             <img src={require("../resources/images/restaurants/restaurant6.png")}
//                                                 class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest6-picture" />
//                                         </a>
//                                         <div class="card-body">
//                                             <div class="card-title name">28 - 50 Marylebone</div>
//                                             <div class="review-count card-text">2096 reviews</div>
//                                             <div class="type card-text">Kebab</div>

//                                             <div class="card-text d-flex location">
//                                                 <img class="icon align-self-center" src={LocationIcon}
//                                                     alt="location-icon" />
//                                                 Tehran
//                                             </div>
//                                             <div class="card-text d-flex time">
//                                                 <div class="open">Open</div>
//                                                 <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                                 <div class="close-time">Closes at 11 PM</div>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>

//                         <div class="result-title">You might also like</div>
//                         <div
//                             class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
//                             <div class="col">
//                                 <div class="restaurant card rounded-4 h-100 position-relative">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant1.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest1_picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">The Ivy Brasserie</div>
//                                         <div class="review-count card-text">2096 reviews</div>
//                                         <div class="type card-text">Contemporary British</div>

//                                         <div class="card-text d-flex location">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Tehran
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="open">Open</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Closes at 11 PM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div class="col">
//                                 <div class="card rounded-4 h-100 position-relative restaurant">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant2.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">Halia Restaurant</div>
//                                         <div class="review-count card-text">12096 reviews</div>
//                                         <div class="type card-text">Fast Food</div>

//                                         <div class="card-text location d-flex ">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Tehran
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="closed">Closed</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Opens at 10 AM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div class="col">
//                                 <div class=" position-relative restaurant card rounded-4 h-100">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant3.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest3_picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">Eline Cafe</div>
//                                         <div class="review-count card-text">0 reviews</div>
//                                         <div class="type card-text">Sea Food</div>

//                                         <div class="card-text d-flex location">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Rasht
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="open">Open</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Closes at 12 PM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>

//                             <div class="col">
//                                 <div class="restaurant card rounded-4 h-100 position-relative">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant4.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest4-picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">Cecconi's</div>
//                                         <div class="review-count card-text">2096 reviews</div>
//                                         <div class="type card-text">Healthy Food</div>

//                                         <div class="card-text d-flex location">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Ahvaz
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="open">Open</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Closes at 11 PM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div class="col">
//                                 <div class="restaurant card rounded-4 h-100 position-relative">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant5.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest5-picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">Hard Rock Cafe</div>
//                                         <div class="review-count card-text">2096 reviews</div>
//                                         <div class="type card-text">Vegetarian Food</div>

//                                         <div class="card-text d-flex location">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Tabriz
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="open">Open</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Closes at 11 PM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div class="col">
//                                 <div class="restaurant card rounded-4 h-100 position-relative">
//                                     <a href="#" class="restaurant-link">
//                                         <div class="rate d-flex position-absolute px-2 py-1">
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                             <img class="icon" src={StarFilled} alt="star_empty" />
//                                             <img class="icon" src={StarFilled} alt="star_filled" />
//                                         </div>
//                                         <img src={require("../resources/images/restaurants/restaurant6.png")}
//                                             class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest6-picture" />
//                                     </a>
//                                     <div class="card-body">
//                                         <div class="card-title name">28 - 50 Marylebone</div>
//                                         <div class="review-count card-text">2096 reviews</div>
//                                         <div class="type card-text">Kebab</div>

//                                         <div class="card-text d-flex location">
//                                             <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
//                                             Tehran
//                                         </div>
//                                         <div class="card-text d-flex time">
//                                             <div class="open">Open</div>
//                                             <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
//                                             <div class="close-time">Closes at 11 PM</div>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//                 <div class="container">
//                     <div class="row mt-0">
//                         <div class="col">
//                             <img src={require("../resources/images/tables_about.png")} alt="tables_logo" class="col" id="table-image" />
//                         </div>
//                         <div class="col mt-5 general-text">
//                             <h3 id="about" class="general-text">About Mizdooni</h3>
//                             <span id="about-text">Are you tired of waiting in long lines at restaurants or
//                                 struggling to find a table at your favorite eatery? <br />
//                                 <br />
//                                 Look no further than Mizdooni – the ultimate solution for all your
//                                 dining reservation needs.
//                                 <br />
//                                 <br />
//                                 Mizdooni is a user-friendly website where you can reserve a table
//                                 at any restaurant, from anywhere, at a specific time. Whether
//                                 you're craving sushi, Italian, or a quick bite to eat, Mizdooni
//                                 has you covered.
//                                 <br />
//                                 <br />
//                                 With a simple search feature, you can easily find a restaurant
//                                 based on cuisine or location. <br />
//                                 <br />
//                                 <span id="red-text"> The best part? </span> There are no fees
//                                 for making a reservation through Mizdooni. Say goodbye to the
//                                 hassle of calling multiple restaurants or showing up only to find
//                                 there's a long wait. With Mizdooni, you can relax knowing that
//                                 your table is secured and waiting for you. <br />
//                                 <br />
//                                 Don't let dining out be a stressful experience. Visit Mizdooni
//                                 today and start enjoying your favorite meals without the headache
//                                 of making reservations. Reserve your table with ease and dine with
//                                 peace of mind.</span>
//                         </div>
//                     </div>
//                 </div>
//             </main>

//             <footer class="text-center p-2 m-0 mt-5" id="footer">
//                 <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
//             </footer>
//         </div>
//     );
// }

export default HomePage;



// export default class Home extends React.Component {

//     constructor(props) {
//         super(props);
//         this.state = {
//             studentInfo: undefined,
//             test: 1
//         }
//         this.updateStudentInfo = this.updateStudentInfo.bind(this);
//         this.reportCards = this.updateReportCards.bind(this);

//     }

//     updateStudentInfo() {
//         API.get("student/", {headers: authHeader()}).then(
//             jsonData => {
//                 this.setState({studentInfo: jsonData.data});
//         }).catch(error => {
//             if(error.response.status == 401 || error.response.status == 403)
//                 window.location.href = "http://localhost:3000/login"
//             console.log('rid')
//         })
//     }

//     updateReportCards() {
//         API.get("student/reportCards", {headers: authHeader()}).then(
//             jsonData => {
//                 this.setState({reportCards: jsonData.data});
//         }).catch(error => {
//             if(error.response.status == 401 || error.response.status == 403)
//                 window.location.href = "http://localhost:3000/login"
//         })
//     }

//     componentDidMount() {
//         document.title = "Home";
//         this.updateStudentInfo()
//         this.updateReportCards()
//     }

//     render() {
//         return (
//             <div className="main-container">
//                 <Header firstOption={"انتخاب واحد"}
//                         secondOption={"برنامه هفتگی"}
//                         firstRoute={"/courses"}
//                         secondRoute={"/schedule"}/>
//                 <main>
//                 <HomeTopSection/>
//                 <div className="container-fluid text-center">
//                     <div className="main row">
//                     <ProfileInfo studentInfo = {this.state.studentInfo}/>
//                     <ReportCards reportCards = {this.state.reportCards}/>
//                     </div>
//                 </div>
//                 </main>
//                 <Footer/>
//             </div>
//         );
//     }

// }