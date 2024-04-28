import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, } from "react-router-dom";
import { useLayoutEffect } from 'react';

import "../resources/styles/home_page.css"

// images
import StarEmpty from "../resources/images/icons/star_empty.svg";
import StarFilled from "../resources/images/icons/star_filled.svg";
import LocationIcon from "../resources/images/icons/location.svg";

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

                <Header />

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
    return (
        <div>
            <div id="top-restaurants-id">
                <div class="result-title">Top restaurants in Mizdooni</div>
            </div>
            <div id="seggested-top-restaurants-id">
                <div
                    class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">

                    <RestaurantCard
                        numberOfStars="1"
                        restaurantName="Haida Restaurant"
                        reviewCount="12096"
                        restaurantType="Fast Food"
                        location="Tehran"
                        openStatus="Open"
                        durationInfo="Opens at 10 AM"
                    />

                    <RestaurantCard
                        numberOfStars="3"
                        restaurantName="Eline Cafe"
                        reviewCount="0"
                        restaurantType="Sea Food"
                        location="Rasht"
                        openStatus="Open"
                        durationInfo="Closes at 12 PM"
                    />

                    <RestaurantCard
                        numberOfStars="4"
                        restaurantName="Cecconi's"
                        reviewCount="2096"
                        restaurantType="Healthy Food"
                        location="Ahvaz"
                        openStatus="Open"
                        durationInfo="Closes at 11 PM"
                    />
                    
                </div>
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


function RestaurantCard(props) {

    let numberOfStars, restaurantName, reviewCount, restaurantType, location, openStatus, durationInfo;
    numberOfStars = props.numberOfStars;
    restaurantName = props.restaurantName;
    reviewCount = props.reviewCount;
    restaurantType = props.restaurantType;
    location = props.location;
    openStatus = props.openStatus;
    durationInfo = props.durationInfo;

    return (
        <div class="col">
            <div class="restaurant card rounded-4 h-100 position-relative">
                <a href="#" class="restaurant-link">
                    <div class="rate d-flex position-absolute px-2 py-1">
                        <Stars numberOfStars={numberOfStars} />
                    </div>
                    <img src={require("../resources/images/restaurants/restaurant2.png")}
                        class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
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
                        {openStatus == "Open" ? <div class="open">Open</div> :  <div class="closed">Closed</div>}
                        <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                        <div class="close-time">{durationInfo}</div>
                    </div>
                </div>
            </div>
        </div>

    );
}

function SuggestedRestaurants() {
    return (
        <div>


            <div class="result-title">You might also like</div>
            <div
                class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
                <div class="col">
                    <div class="restaurant card rounded-4 h-100 position-relative">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant1.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest1_picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">The Ivy Brasserie</div>
                            <div class="review-count card-text">2096 reviews</div>
                            <div class="type card-text">Contemporary British</div>

                            <div class="card-text d-flex location">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Tehran
                            </div>
                            <div class="card-text d-flex time">
                                <div class="open">Open</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Closes at 11 PM</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card rounded-4 h-100 position-relative restaurant">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant2.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">Halia Restaurant</div>
                            <div class="review-count card-text">12096 reviews</div>
                            <div class="type card-text">Fast Food</div>

                            <div class="card-text location d-flex ">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Tehran
                            </div>
                            <div class="card-text d-flex time">
                                <div class="closed">Closed</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Opens at 10 AM</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class=" position-relative restaurant card rounded-4 h-100">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant3.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest3_picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">Eline Cafe</div>
                            <div class="review-count card-text">0 reviews</div>
                            <div class="type card-text">Sea Food</div>

                            <div class="card-text d-flex location">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Rasht
                            </div>
                            <div class="card-text d-flex time">
                                <div class="open">Open</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Closes at 12 PM</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="restaurant card rounded-4 h-100 position-relative">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant4.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest4-picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">Cecconi's</div>
                            <div class="review-count card-text">2096 reviews</div>
                            <div class="type card-text">Healthy Food</div>

                            <div class="card-text d-flex location">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Ahvaz
                            </div>
                            <div class="card-text d-flex time">
                                <div class="open">Open</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Closes at 11 PM</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="restaurant card rounded-4 h-100 position-relative">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant5.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest5-picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">Hard Rock Cafe</div>
                            <div class="review-count card-text">2096 reviews</div>
                            <div class="type card-text">Vegetarian Food</div>

                            <div class="card-text d-flex location">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Tabriz
                            </div>
                            <div class="card-text d-flex time">
                                <div class="open">Open</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Closes at 11 PM</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="restaurant card rounded-4 h-100 position-relative">
                        <a href="#" class="restaurant-link">
                            <div class="rate d-flex position-absolute px-2 py-1">
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                                <img class="icon" src={StarFilled} alt="star_empty" />
                                <img class="icon" src={StarFilled} alt="star_filled" />
                            </div>
                            <img src={require("../resources/images/restaurants/restaurant6.png")}
                                class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest6-picture" />
                        </a>
                        <div class="card-body">
                            <div class="card-title name">28 - 50 Marylebone</div>
                            <div class="review-count card-text">2096 reviews</div>
                            <div class="type card-text">Kebab</div>

                            <div class="card-text d-flex location">
                                <img class="icon align-self-center" src={LocationIcon} alt="location-icon" />
                                Tehran
                            </div>
                            <div class="card-text d-flex time">
                                <div class="open">Open</div>
                                <img class="icon align-self-center" src="../resources/images/icons/dot.svg" alt="dot-icon" />
                                <div class="close-time">Closes at 11 PM</div>
                            </div>
                        </div>
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
