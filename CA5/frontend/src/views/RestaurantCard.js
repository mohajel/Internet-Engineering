
import React from 'react';
import { useNavigate } from 'react-router-dom';
import LocationIcon from "../resources/images/icons/location.svg";

import Stars from './Stars';


function RestaurantCard(props) {

    let navigate = useNavigate();

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
    

    return (
        <div class="col">
            <div class="restaurant card rounded-4 h-100 position-relative">
                <a href={reference} class="restaurant-link">
                {/* <button onClick={handleCardClick(reference)} class="restaurant-link"> */}
                    <div class="rate d-flex position-absolute px-2 py-1">
                        <Stars numberOfStars={numberOfStars} />
                    </div>
                    <div class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture">
                        {/* <RandomRestaurantImage imgUrl={imgUrl} /> */}
                        <img src={imgUrl} class="restaurant-img card-img-top object-fit-cover w-100 rounded-top-4" alt="rest2_picture" />
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


export default RestaurantCard;