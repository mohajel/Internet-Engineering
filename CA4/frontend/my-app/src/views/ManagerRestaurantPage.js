
import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { postReq, getReq } from '../utils/api';

import "../resources/styles/manager_restaurants_page.css"
import Header from './Header';

function ManagerRestaurantPage(props) {

    let name = props.username;

    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/reviews/summary/" + name)

            .then(response => {
                console.log(response);
                setInfo(response);
                setLoading(false);
            });
    }, []);

    return (
        <>
            {
                loading ?
                    <div>Loading...</div>
                    :
                    <div class="review-box container w-75 align-middle">
                        <div class="container mt-2 p-3">
                            <div class="row position-relative">
                                <div class="col">
                                    <div>
                                        <h4>What {info.reviewsCount} people are saying</h4>
                                        <div class="container rounded-4 d-flex justify-content-start">
                                            <p class="text-muted p-0 ms-3">{info.overallRate} based on recent ratings</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="d-flex justify-content-between">
                                        <div class="text-center">
                                            <p class="mb-0">Food</p>
                                            <p class="fw-bolder">{info.foodRate}</p>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0">Service</p>
                                            <p class="fw-bolder">{info.serviceRate}</p>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0">Ambience</p>
                                            <p class="fw-bolder">{info.ambianceRate}</p>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0">Overall</p>
                                            <p class="fw-bolder">{info.overallRate}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            }

        </>
    );



    return (
        <div class="d-flex flex-column">
            <Header />

            <main class="flex-grow-1">
                <div class="p-3 container">
                    <div class="table-responsive mx-auto w-50 mt-4 table-container p-0">
                        <table class="table align-middle">
                            <thead class="restaurant-header">
                                <tr>
                                    <th colspan="2" scope="colgroup">My Restaurants</th>
                                    <td class="text-end">
                                        <button class="manager-operation add rounded-3 border-0">
                                            Add
                                        </button>
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="restaurant-row">
                                    <td class="restaurant-name">Ali Daei Dizy</td>
                                    <td class="restaurant-address text-center">Boshehr, Iran</td>
                                    <td class="text-end">
                                        <button class="manager-operation manage rounded-3 border-0">
                                            Manage
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                            <tbody>
                                <tr class="restaurant-row">
                                    <td class="restaurant-name">Ali Daei Dizy</td>
                                    <td class="restaurant-address text-center">Boshehr, Iran</td>
                                    <td class="text-end">
                                        <button class="manager-operation manage rounded-3 border-0">
                                            Manage
                                        </button>
                                    </td>
                                </tr>
                            </tbody>

                        </table>
                    </div>
                </div>
            </main>

            <footer class="text-center p-2 m-0" id="footer">
                <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
            </footer>
        </div>
    );

}

export default ManagerRestaurantPage;
