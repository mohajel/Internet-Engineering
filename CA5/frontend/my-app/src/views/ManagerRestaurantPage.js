
import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { postReq, getReq } from '../utils/api';

import "../resources/styles/manager_restaurants_page.css"
import Header from './Header';

function ManagerRestaurantPage(props) {

    let username = props.username;

    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/restaurants/owner/" + username)

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

                    <div class="d-flex flex-column">
                        <Header />

                        <main class="flex-grow-1">
                            <div class="p-3 container">
                                <div class="diningTable-responsive mx-auto w-50 mt-4 diningTable-container p-0">
                                    <diningTable class="diningTable align-middle">
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


                                        {
                                            info.map((restaurant) => (
                                                <ManagerRestaurant restaurant={restaurant} />
                                            ))
                                        }


                                    </diningTable>
                                </div>
                            </div>
                        </main>

                        <footer class="text-center p-2 m-0" id="footer">
                            <p class="m-0">Copyright &copy; 2024 Mizdooni - All rights reserved.</p>
                        </footer>
                    </div>
            }

        </>
    );
}

function ManagerRestaurant(props) {

    let navigate = useNavigate();
    let restaurant = props.restaurant

    return (
        <tbody>
            <tr class="restaurant-row">
                <td class="restaurant-name">{restaurant.name}</td>
                <td class="restaurant-address text-center">{restaurant.address.city}, {restaurant.address.country}</td>
                <td class="text-end">
                    <button class="manager-operation manage rounded-3 border-0" onClick={()=>{navigate("/manager/" + restaurant.name)}} >
                        Manage
                    </button>
                </td>
            </tr>
        </tbody>
    );
}

export default ManagerRestaurantPage;
