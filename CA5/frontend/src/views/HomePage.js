import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, } from "react-router-dom";
import { useLayoutEffect } from 'react';
import { postReq, getReq } from '../utils/api';

import "../resources/styles/home_page.css"

// images

// import Restaurant1Img from "../resources/images/restaurants/restaurant1.png";
// import Restaurant2Img from "../resources/images/restaurants/restaurant2.png";
// import Restaurant3Img from "../resources/images/restaurants/restaurant3.png";
// import Restaurant4Img from "../resources/images/restaurants/restaurant4.png";
// import Restaurant5Img from "../resources/images/restaurants/restaurant5.png";
// import Restaurant6Img from "../resources/images/restaurants/restaurant6.png";


import Header from './Header';
import RestaurantCard from './RestaurantCard';

const MessagePage = React.lazy(() => import('./MessagePage'));
const ManagerRestaurantPage = React.lazy(() => import('./ManagerRestaurantPage'))



// default of how to use fetch

// const navigate = useNavigate();

// const [user, setUser] = useState([]);
// useEffect(() => {
//    fetch('/abcde')
//       .then((response) => response.json())
//       .then((data) => {
//          console.log(data);
//          setUser(data);
//       })
//       .catch((err) => {
//          console.log(err.message);
//       });
// }, []);

// return (
//    <h1>salam <div><pre>{JSON.stringify(user, null, 2)}</pre></div></h1>
// );


function HomePage() {

    const [mounted, setMounted] = useState(false);
    const [status, setStatus] = useState([]);

    if (!mounted) {
        // Code for componentWillMount here
        // This code is called only one time before initial render
        fetch("/status")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setStatus(data);
            })
            .catch((err) => {
                console.log(err.message);
            });
    }

    useEffect(() => {
        setMounted(true)
    }, [])


    if (status.status == "loggedOut") {
        return (
            <MessagePage type='info' message='please login first to continue' redirectURL='/login' />
        )
    }
    else if (status.status == "loggedIn" && status.role == "CLIENT") {
        return (
            <div class="d-flex flex-column">

                <Header buttonText="logout" />

                <main class="flex-grow-1">

                    <SearchRestaurants />

                    <div class="p-3 container">
                        <div class="p-3 container" id="suggestions-container">
                            <TopRestaurants />
                            <SuggestedRestaurants />
                        </div>
                    </div>

                    <AboutMizdooni />

                </main>

                <footer class="text-center p-2 m-0 mt-5" id="footer">
                    <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
                </footer>
            </div>
        );
    } else if (status.status == "loggedIn" && status.role == "MANAGER") {
        return(
            <ManagerRestaurantPage username={status.username}/>
        );
    } else {
        return (
            // <MessagePage type='error' message='unknown Error happened' redirectURL='/login' />
            <div>
                Loading: {status.role}
            </div>
        )
    }
}

function SearchRestaurants() {

    const [restaurantType, setRestaurantType] = useState('');
    const [location, setLocation] = useState('');
    const [restaurantName, setRestaurantName] = useState('');

    const navigate = useNavigate();

    function handleSearchRestaurant() {
        navigate("/search",
            {
                state: { location: location, restaurantType: restaurantType, restaurantName: restaurantName }
            })
    }

    return (
        <div class="container-s w-100 text-center">
            <div class="home-background w-100">
                <div class="container">
                    <div class="input-group mb-3">
                        <img class="big-logo" src={require("../resources/images/logo.png")} alt="logo" />
                    </div>
                    <div class="input-group mb-1 text-center">
                        <select value={location} onChange={(event) => setLocation(event.target.value)} class="custom-select rounded-4 search-input" id="inputGroupSelect01" aria-placeholder="Location">
                            <option selected>Location</option>
                            <option value="Tokyo">Tokyo</option>
                            <option value="Hamburg">Hamburg</option>
                            <option value="Calgary">Calgary</option>
                            <option value="Frankfurt">Frankfurt</option>
                            <option value="Pittsburgh">Pittsburgh</option>
                        </select>

                        <select value={restaurantType} onChange={(event) => setRestaurantType(event.target.value)} class="custom-select rounded-4 search-input" id="RestaurantType">
                            <option selected>Restaurant</option>
                            <option value="American">American</option>
                            <option value="Steakhouse">Steakhouse</option>
                            <option value="Steak">Steak</option>
                            <option value="Seafood">Seafood</option>
                            <option value="French">French</option>
                            <option value="Italian">Italian</option>
                            <option value="Japanese">Japanese</option>
                        </select>

                        <input value={restaurantName} onChange={(event) => setRestaurantName(event.target.value)} type="text" class="search-input restaurant-search rounded-4" placeholder="Type Restaurant ..."
                            aria-label="restaurant search" aria-describedby="basic-addon2" />

                        <button class="btn search-input btn-outline-secondary rounded-4 search-button" type="submit" onClick={handleSearchRestaurant}>
                            Search
                        </button>
                    </div>
                </div>
            </div>
        </div>

    );
}

function TopRestaurants() {

    const navigate = useNavigate();

    const [topRestaurants, setTopRestaurants] = useState([]);
    useEffect(() => {
        fetch("/restaurants/topRated")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setTopRestaurants(data);
                console.log(topRestaurants);
            })
            .catch((err) => {
                console.log(err.message);
            });
    }, []);

    return (
        <div>
            <div id="top-restaurants-id">
                <div class="result-title">Top restaurants in Mizdooni</div>
            </div>
            <div id="seggested-top-restaurants-id">
                <div
                    class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">


                    {topRestaurants.map((restaurant) => (
                        <RestaurantCard
                            numberOfStars={restaurant.numberOfStars}
                            restaurantName={restaurant.restaurantName}
                            reviewCount={restaurant.reviewCount}
                            restaurantType={restaurant.restaurantType}
                            location={restaurant.location}
                            openStatus={restaurant.openStatus}
                            durationInfo={restaurant.durationInfo}
                            imgUrl={restaurant.imgURL}
                        />
                    ))}

                </div>
            </div>
        </div>
    );
}

function SuggestedRestaurants() {

    const [topRestaurants, setTopRestaurants] = useState([]);
    useEffect(() => {
        getReq("/restaurants/suggested")
            .then(response => {
                setTopRestaurants(response);
            });

        // fetch("/restaurants/suggested")
        //     .then((response) => response.json())
        //     .then((data) => {
        //         console.log(data);
        //         setTopRestaurants(data);
        //     })
        //     .catch((err) => {
        //         console.log(err.message);
        //     });
    }, []);



    return (
        <div>
            <div class="result-title">You might also like</div>
            <div
                class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">


                {topRestaurants.map((restaurant) => (
                    <RestaurantCard
                        numberOfStars={restaurant.numberOfStars}
                        restaurantName={restaurant.restaurantName}
                        reviewCount={restaurant.reviewCount}
                        restaurantType={restaurant.restaurantType}
                        location={restaurant.location}
                        openStatus={restaurant.openStatus}
                        durationInfo={restaurant.durationInfo}
                        imgUrl={restaurant.imgURL}
                    />
                ))}
            </div>
        </div>
    );
}


function RandomRestaurantImage(props) {
    const pictureNum = Math.floor(Math.random() * 5) + 1;

    let imgUrl = props.imgUrl;

    return (
        // <div>
        //     salam e2wr34r34 {imgUrl}
        // </div>
        <img src={imgUrl} class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
        // <img src={
        //     pictureNum == 1 ? Restaurant1Img :
        //         pictureNum == 2 ? Restaurant2Img :
        //             pictureNum == 3 ? Restaurant3Img :
        //                 pictureNum == 4 ? Restaurant4Img :
        //                     pictureNum == 5 ? Restaurant5Img : Restaurant6Img
        // }
        //     class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
    );
}

function AboutMizdooni() {
    return (
        <div class="container">
            <div class="row mt-0">
                <div class="col">
                    <img src={require("../resources/images/tables_about.png")} alt="tables_logo" class="col" id="diningTable-image" />
                </div>
                <div class="col mt-5 general-text">
                    <h3 id="about" class="general-text">About Mizdooni</h3>
                    <span id="about-text">Are you tired of waiting in long lines at restaurants or
                        struggling to find a diningTable at your favorite eatery? <br />
                        <br />
                        Look no further than Mizdooni – the ultimate solution for all your
                        dining reservation needs.
                        <br />
                        <br />
                        Mizdooni is a user-friendly website where you can reserve a diningTable
                        at any restaurant, from anywhere, at a specific time. Whether
                        you're craving sushi, Italian, or a quick bite to eat, Mizdooni
                        has you covered.
                        <br />
                        <br />
                        With a simple search feature, you can easily find a restaurant
                        based on cuisine or location. <br />
                        <br />
                        <span id="red-text"> The best part? </span> There are no fees
                        for making a reservation through Mizdooni. Say goodbye to the
                        hassle of calling multiple restaurants or showing up only to find
                        there's a long wait. With Mizdooni, you can relax knowing that
                        your diningTable is secured and waiting for you. <br />
                        <br />
                        Don't let dining out be a stressful experience. Visit Mizdooni
                        today and start enjoying your favorite meals without the headache
                        of making reservations. Reserve your diningTable with ease and dine with
                        peace of mind.</span>
                </div>
            </div>
        </div>
    );
}

export default HomePage;
