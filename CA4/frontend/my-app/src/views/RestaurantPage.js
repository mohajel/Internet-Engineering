
import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { postReq, getReq } from '../utils/api';
import { unstable_useViewTransitionState, useParams } from 'react-router-dom';

import "../resources/styles/restaurant_page.css"
import Header from './Header';

import ClockImg from "../resources/images/icons/clock.svg"
import ForkImg from "../resources/images/icons/fork_knife.svg"

function RestaurantPage() {

    let { name } = useParams();

    // return(
    //     <div>
    //         salam :: {name}
    //     </div>
    // )

    return (

        <div class="d-flex flex-column">
            <Header />

            <main class="flex-grow-1">

                <div class="row mt-0 position-relative ms-0">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col d-flex flex-column flex-md-row column">

                                <RestaurantInfoPart name={name} />
                                <ReservePart />
                            </div>
                        </div>
                    </div>
                    <ReviewPart />

                </div>
            </main>

            <footer class="text-center p-2 m-0 mt-5" id="footer">
                <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
            </footer>
        </div>


    );

}

function RestaurantInfoPart(props) {

    let name = props.name;

    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/restaurants/data/" + name)

            .then(response => {
                console.log(response);
                setInfo(response);
                setLoading(false);
            });
    }, []);

    // return (
    //     <h1>salam <div><pre>{JSON.stringify(info, null, 2)}</pre></div></h1>
    // )

    // {
    //     "address": {
    //       "city": "Pittsburgh",
    //       "country": "US",
    //       "street": "620 William Penn Place"
    //     },
    //     "description": "At our gastropub, we don't distinguish between commoners and kings; we just want to feed the good people of Pittsburgh. In the restaurant, seasonal menus add a modern flair to classic comforts, complemented by a robust selection of local beers and craft spirits. It's all served in an industrial-inspired setting in downtown Pittsburgh. Come and join us for an uncommonly good time.",
    //     "endTime": "23:00",
    //     "image": "https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp",
    //     "managerUsername": "ali",
    //     "name": "The Commoner",
    //     "startTime": "07:00",
    //     "type": "American"
    //   }

    return (
        <>
            {loading ? <div>Loading...</div>
                :
                <div class="flex-even p-3 mx-2">
                    <img src={info.image} alt="tables_logo" class="position-relative col rounded-3 img-fluid" id="sample-rest-img" />
                    <div class="descripton-card position-relative">
                        <div class="position-relative d-inline-flex">
                            <h1 id="restaurant-name">{info.name}</h1>
                        </div>
                        <div class="container">
                            <div class="review-card">
                                <div class="review-header">
                                    <div class="d-flex">
                                        <img class="icon p-0" src={ClockImg} alt="star_filled" />
                                        <div class="timing p-0">From {info.startTime} to {info.endTime}</div>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <div class="icon-container text-center">
                                            <img class="star-review" src={require("../resources/images/icons/star_inside_review.png")} alt="review" />
                                        </div>
                                        <div class="rating p-0">160 Reviews</div>
                                    </div>
                                    <div class="d-flex me-5">
                                        <img class="icon" src={ForkImg} alt="star_filled" />
                                        <div class="type">{info.type}</div>
                                    </div>
                                </div>
                                <div class="restaurant-location">
                                    <img class="icon align-self-center" src={require("../resources/images/icons/location2.png")} alt="location-icon" />
                                    <span class="text-muted">{info.address.country}, {info.address.city}, {info.address.street}</span>
                                </div>
                                <div class="review-content">
                                    <p>
                                        {info.description}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            }

        </>


    );
}

function ReservePart() {
    return (
        <div class="flex-even p-3 mx-2">
            <h5 id="booking" class="general-text">Reserve Table</h5>
            <div class="d-inline-flex">
                <span class="text-center mt-1"> For </span>
                <div class="input-group text-center">
                    <select class="custom-select count-picker rounded-3 w-10 ms-2" id="inputGroupSelect01" aria-placeholder="Location">
                        <option selected>2</option>
                        <option value="if">3</option>
                    </select>
                    <span class="text-center mt-1 ms-2"> people, on date </span>
                    <button class="btn date-picker btn-outline-seco ndary ms-2 search-button" type="button">
                        <div class="d-flex">
                            <img class="icon p-0" src="../resources/images/icons/calendar.svg" alt="star_filled" />
                            <span class="p-0 ms-3 fw-bolder"> 2024-02-18 </span>
                        </div>
                    </button>
                </div>
            </div>
            <br />
            <br />
            <span>Available Times for Table #1 (2 seats)</span>
            <div class="container w-75 text-center mt-4 ms-0">
                <div class="row">
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">
                        11:00AM
                    </div>
                    <div class="selected-reserved-blob col-sm ms-2 rounded-4 mt-2">
                        12:00PM
                    </div>
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">13:00PM</div>
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">14:00PM</div>
                </div>
                <div class="row">
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">15:00PM</div>
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">18:00PM</div>
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">19:00PM</div>
                    <div class="reserve-blob col-sm ms-2 rounded-4 mt-2">20:00PM</div>
                </div>
                <div class="general-text w-100 mt-3 mb-3">
                    <p class="fw-bolder red-text">
                        You will reserve this table only for <u>one</u> hour, for more
                        time please contact the restaurant.
                    </p>
                </div>
                <div class="row">
                    <div class="selected-reserved-blob col-sm ms-2 rounded-4">
                        Complete the reservation
                    </div>
                </div>
            </div>

        </div>


    );
}

function ReviewPart() {
    return (
        <>
            <div class="review-box container w-75 align-middle">
                <div class="container mt-2 p-3">
                    <div class="row position-relative">
                        <div class="col">
                            <div>
                                <h4>What 160 people are saying</h4>
                                <div class="container rounded-4 d-flex justify-content-start">
                                    <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                    <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled"
                                    />
                                    <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                    <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                    <img class="icon p-0" src="../resources/images/icons/star_empty.svg" alt="star_filled" />
                                    <p class="text-muted p-0 ms-3">4 based on recent ratings</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="d-flex justify-content-between">
                                <div class="text-center">
                                    <p class="mb-0">Food</p>
                                    <p class="fw-bolder">4.5</p>
                                </div>
                                <div class="text-center">
                                    <p class="mb-0">Service</p>
                                    <p class="fw-bolder">4.1</p>
                                </div>
                                <div class="text-center">
                                    <p class="mb-0">Ambience</p>
                                    <p class="fw-bolder">3.8</p>
                                </div>
                                <div class="text-center">
                                    <p class="mb-0">Overall</p>
                                    <p class="fw-bolder">4</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="review-pages mt-5 container align-middle">
                <div class="row">
                    <div class="col">
                        <div class="review-container">
                            <div
                                class="container d-flex justify-content-between align-items-center my-3"
                            >
                                <div class="rounded-4"><h4>160 Reviews</h4></div>
                                <button class="add-review-btn rounded-4">Add Review</button>
                            </div>

                            <div class="container">
                                <div class="review-header">
                                    <div class="profile-photo d-flex">
                                        <span class="fw-bolder profile-name general-text position-relative">AD</span>
                                    </div>
                                    <span class="fw-bolder profile-name general-text review-namer">Ali Daei</span>

                                    <div>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <span class="review-date ms-2">Dined on February 17, 2024</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container ms-5">
                                    <div class="fw-bolder">
                                        <small>Overall<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Food<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Service<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Ambiance<span class="red-text ms-1">5 </span>
                                        </small>
                                    </div>
                                    <div class="review-content fw-bolder mt-2">
                                        <p>
                                            Excellent pre-theatre meal. Good food and service. Only
                                            small criticism is that music was intrusive.
                                        </p>
                                    </div>
                                </div>
                                <hr />
                            </div>
                            <div class="container">
                                <div class="review-header">
                                    <div class="profile-photo d-flex">
                                        <span class="fw-bolder profile-name general-text position-relative">AD</span>
                                    </div>
                                    <span class="fw-bolder profile-name general-text review-namer">Ali Dari</span>

                                    <div>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_empty.svg" alt="star_filled" />
                                            <span class="review-date ms-2">Dined on February 17, 2024</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container ms-5">
                                    <div class="fw-bolder">
                                        <small>Overall<span class="red-text ms-1">4 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Food<span class="red-text ms-1">4 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Service<span class="red-text ms-1">4 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Ambiance<span class="red-text ms-1">4 </span>
                                        </small>
                                    </div>
                                    <div class="review-content fw-bolder mt-2">
                                        <p>
                                            Excellent pre-theatre meal. Good food and service. Only
                                            small criticism is that music was intrusive.
                                        </p>
                                    </div>
                                </div>
                                <hr />
                            </div>
                            <div class="container">
                                <div class="review-header">
                                    <div class="profile-photo d-flex">
                                        <span
                                            class="fw-bolder profile-name general-text position-relative">AD</span>
                                    </div>
                                    <span
                                        class="fw-bolder profile-name general-text review-namer">Ali Daryaei</span>

                                    <div>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <span class="review-date ms-2">Dined on February 17, 2024</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container ms-5">
                                    <div class="fw-bolder">
                                        <small>Overall<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Food<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Service<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Ambiance<span class="red-text ms-1">5 </span>
                                        </small>
                                    </div>
                                    <div class="review-content fw-bolder mt-2">
                                        <p>
                                            Excellent pre-theatre meal. Good food and service. Only
                                            small criticism is that music was intrusive.
                                        </p>
                                    </div>
                                </div>
                                <hr />
                            </div>
                            <div class="container">
                                <div class="review-header">
                                    <div class="profile-photo d-flex">
                                        <span
                                            class="fw-bolder profile-name general-text position-relative">AD</span>
                                    </div>
                                    <span class="fw-bolder profile-name general-text review-namer">Ali Daryaei</span>

                                    <div>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <span class="review-date ms-2">Dined on February 17, 2024</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container ms-5">
                                    <div class="fw-bolder">
                                        <small>Overall<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Food<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Service<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Ambiance<span class="red-text ms-1">5 </span>
                                        </small>
                                    </div>
                                    <div class="review-content fw-bolder mt-2">
                                        <p>
                                            Excellent pre-theatre meal. Good food and service. Only
                                            small criticism is that music was intrusive.
                                        </p>
                                    </div>
                                </div>
                                <hr />
                            </div>
                            <div class="container">
                                <div class="review-header">
                                    <div class="profile-photo d-flex">
                                        <span
                                            class="fw-bolder profile-name general-text position-relative">AD</span>
                                    </div>
                                    <span
                                        class="fw-bolder profile-name general-text review-namer">Ali Daryaeip</span>

                                    <div>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <img class="icon p-0" src="../resources/images/icons/star_filled.svg" alt="star_filled" />
                                            <span class="review-date ms-2">Dined on February 17, 2024</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container ms-5">
                                    <div class="fw-bolder">
                                        <small>Overall<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Food<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Service<span class="red-text ms-1">5 </span>
                                            <span>&#183;</span>
                                        </small>
                                        <small class="ms-1">Ambiance<span class="red-text ms-1">5 </span>
                                        </small>
                                    </div>
                                    <div class="review-content fw-bolder mt-2">
                                        <p>
                                            Excellent pre-theatre meal. Good food and service. Only
                                            small criticism is that music was intrusive.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rounded-4 d-flex fw-bold justify-content-center mt-5">
                            <span class="page-button text-center">
                                <span class="page-no position-relative">1</span></span>
                            <span class="page-button text-center ms-3">
                                <span class="page-no position-relative">2</span></span>
                            <span class="page-button text-center ms-3">
                                <span class="page-no position-relative">3 </span></span>
                            <span class="ms-3 mt-3">
                                <span>&#183;</span>
                                <span>&#183;</span>
                                <span>&#183;</span>
                            </span>
                            <span class="page-button text-center ms-3">
                                <span class="page-no position-relative">19 </span></span>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default RestaurantPage;