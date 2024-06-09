import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route, } from "react-router-dom";
import { useLayoutEffect } from 'react';
import { postReq, getReq } from '../utils/api';

import "../resources/styles/search_page.css"


import Header from './Header';
import RestaurantCard from './RestaurantCard';

function SearchRestaurantPage() {

    const location = useLocation();
    console.log('location', location)

    return (
        <div class="d-flex flex-column">
            <Header buttonText="Home" navigateURL="/" />

            <main class="flex-grow-1">
                <div class="p-3 container">
                    <div class="search-result-title">Results for</div>
                    <div> Name: {location.state.restaurantName} </div>
                    <div> location: {location.state.location} </div>
                    <div> type: {location.state.restaurantType} </div>
                    <div class="restaurants row p-2 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
                        <SimilarRestaurantCard restaurantName={location.state.restaurantName}
                            location={location.state.location} restaurantType={location.state.restaurantType} />
                    </div>
                </div>
            </main>

            <footer class="text-center p-2 m-0" id="footer">
                <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
            </footer>
        </div>
    )
}

function SimilarRestaurantCard(props) {

    const [similarRestaurants, setSimilarRestaurants] = useState([]);
    useEffect(() => {

        getReq("/restaurants/search", {
            restaurantName: props.restaurantName,
            location: props.location,
            restaurantType: props.restaurantType
        }).then(response => {
            setSimilarRestaurants(response);
        });

    }, []);

    return (
        <>
            {/* OK {props.restaurantName} {props.location} {props.restaurantType} */}

            {/* if length of similarRestaurants == 0 then show "NO RESULT FOUND" */}
            {

                similarRestaurants.length == 0 ?
                    <div>
                        "NO RESULT FOUND"
                    </div>
                    :
                    similarRestaurants.map((restaurant) => (
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
                    ))

            }
        </>
    )
}

export default SearchRestaurantPage;