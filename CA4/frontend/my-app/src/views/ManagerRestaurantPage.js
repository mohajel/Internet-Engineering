
import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { postReq, getReq } from '../utils/api';

import "../resources/styles/manager_restaurants_page.css"
import Header from './Header';

function ManagerRestaurantPage() {

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
