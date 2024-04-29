import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, } from "react-router-dom";
import { useLayoutEffect } from 'react';
import { postReq, getReq } from '../utils/api';

import "../resources/styles/home_page.css"

// images
import StarEmpty from "../resources/images/icons/star_empty.svg";
import StarFilled from "../resources/images/icons/star_filled.svg";
import LocationIcon from "../resources/images/icons/location.svg";
import Restaurant1Img from "../resources/images/restaurants/restaurant1.png";
import Restaurant2Img from "../resources/images/restaurants/restaurant2.png";
import Restaurant3Img from "../resources/images/restaurants/restaurant3.png";
import Restaurant4Img from "../resources/images/restaurants/restaurant4.png";
import Restaurant5Img from "../resources/images/restaurants/restaurant5.png";
import Restaurant6Img from "../resources/images/restaurants/restaurant6.png";




import Header from './Header';

const MessagePage = React.lazy(() => import('./MessagePage'));


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
    else if (status.status == "loggedIn") {
        return (
            <div class="d-flex flex-column">

                <Header buttonText="LogOut" />

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


    }
}

function SearchRestaurants() {
    return (
        <div class="container-s w-100 text-center">
            <div class="home-background w-100">
                <div class="container">
                    <div class="input-group mb-3">
                        <img class="big-logo" src={require("../resources/images/logo.png")} alt="logo" />
                    </div>
                    <div class="input-group mb-1 text-center">
                        <select class="custom-select rounded-4 search-input" id="inputGroupSelect01" aria-placeholder="Location">
                            <option selected>Location</option>
                            <option value="ff">Tehran</option>
                            <option value="tf">Rasht</option>
                            <option value="if">Gonbad</option>
                        </select>
                        <select class="custom-select rounded-4 search-input" id="RestaurantType">
                            <option selected>Restaurant</option>
                            <option value="ff">Fast Food</option>
                            <option value="tf">Traditional Food</option>
                            <option value="if">Italian Food</option>
                        </select>
                        <input type="text" class="search-input restaurant-search rounded-4" placeholder="Type Restaurant ..."
                            aria-label="restaurant search" aria-describedby="basic-addon2" />
                        <button class="btn search-input btn-outline-secondary rounded-4 search-button" type="button">
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

function Stars(props) {
    const numberOfStars = props.numberOfStars;
    const stars = [];
    for (let i = 0; i < numberOfStars; i++) {
        stars.push(<img key={i} className="icon" src={StarFilled} alt="star_filled" />);
    }

    for (let i = numberOfStars; i < 5; i++) {
        stars.push(<img key={i} className="icon" src={StarEmpty} alt="star_empty" />);
    }
    return stars;
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

function RestaurantCard(props) {

    let numberOfStars, restaurantName, reviewCount, restaurantType, location, openStatus, durationInfo, reference, imgUrl;
    numberOfStars = props.numberOfStars;
    restaurantName = props.restaurantName;
    reviewCount = props.reviewCount;
    restaurantType = props.restaurantType;
    location = props.location;
    openStatus = props.openStatus;
    durationInfo = props.durationInfo;
    reference = "/restaurant/" + restaurantName;
    imgUrl = props.imgUrl;

    // const eImg = () => <ExternalImage src={require(imgUrl)} /> didn't worked



    return (
        <div class="col">
            <div class="restaurant card rounded-4 h-100 position-relative">
                <a href={reference} class="restaurant-link">
                    <div class="rate d-flex position-absolute px-2 py-1">
                        <Stars numberOfStars={numberOfStars} />
                    </div>
                    <div class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture">
                        {/* <eImg /> */}
                        <RandomRestaurantImage imgUrl={imgUrl} />
                    </div>
                </a>
                <div class="card-body">
                    <div class="card-title name">{restaurantName}</div>
                    <div class="review-count card-text">{reviewCount} reviews</div>
                    <div class="type card-text">{restaurantType}</div>

                    <div class="card-text d-flex location">
                        <img class="icon align-self-center" src={LocationIcon}
                            alt="location-icon" />
                        {location}
                    </div>
                    <div class="card-text d-flex time">
                        {openStatus == "Open" ? <div class="open">Open</div> : <div class="closed">Closed</div>}
                        <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                        <div class="close-time">{durationInfo}</div>
                    </div>
                </div>
            </div>
        </div>
    );
}

function AboutMizdooni() {
    return (
        <div class="container">
            <div class="row mt-0">
                <div class="col">
                    <img src={require("../resources/images/tables_about.png")} alt="tables_logo" class="col" id="table-image" />
                </div>
                <div class="col mt-5 general-text">
                    <h3 id="about" class="general-text">About Mizdooni</h3>
                    <span id="about-text">Are you tired of waiting in long lines at restaurants or
                        struggling to find a table at your favorite eatery? <br />
                        <br />
                        Look no further than Mizdooni – the ultimate solution for all your
                        dining reservation needs.
                        <br />
                        <br />
                        Mizdooni is a user-friendly website where you can reserve a table
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
                        your table is secured and waiting for you. <br />
                        <br />
                        Don't let dining out be a stressful experience. Visit Mizdooni
                        today and start enjoying your favorite meals without the headache
                        of making reservations. Reserve your table with ease and dine with
                        peace of mind.</span>
                </div>
            </div>
        </div>
    );
}

export default HomePage;
