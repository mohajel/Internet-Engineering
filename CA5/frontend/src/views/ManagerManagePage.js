
import React, { useLayoutEffect } from 'react';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { unstable_useViewTransitionState, useParams } from 'react-router-dom'

import { postReq, getReq } from '../utils/api';

import "../resources/styles/manager_manage_page.css"
import Header from './Header';

import HashtagImg from "../resources/images/icons/hashtag.svg"
import SeatsImg from "../resources/images/icons/seat.svg"

function ManagerManagePage() {

    let { name } = useParams();


    return (
        <div class="min-vh-100 d-flex flex-column">
            <Header navigateURL="/" buttonText="Restaurants" />

            <main class="flex-grow-1">
                <div class="d-flex align-items-center p-3">
                    <div id="manage-rest-name">{name}</div>
                </div>
                <div class="container-fluid vh-100 d-flex flex-column">
                    <div class="row flex-grow-1">

                        <ReservationInfoPart name={name} />
                        <SeatsPart name={name} />
                    </div>
                    <footer
                        class="text-center sticky-bottom row flex-shrink-0 p-2 m-0"
                        id="footer"
                    >
                        <p class="m-0">
                            Copyright &copy; 2024 Mizdooni - All rights reserved.
                        </p>
                    </footer>
                </div>
            </main>
        </div>
    )
}

function SeatsPart(props) {

    let name = props.name


    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/tables/" + name)

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
                    <div class="col-8 mh-100" id="add-diningTable">
                        <div class="add-diningTable-button-container">
                            <a id="add-column-button">+ Add Table</a>
                        </div>
                        <div class="container p-4">
                            <div class="row w-75 mx-auto row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-xl-5 row-cols-xxl-6 g-4">

                                {
                                    info.map((seatInfo) => (
                                        <Seat info={seatInfo} />
                                    ))
                                }
                            </div>
                        </div>
                    </div>
            }
        </>
    );
}

function Seat(props) {
    return (
        <div class="col">
            <div
                class="rest-diningTable mx-auto justify-content-center d-flex flex-column rounded-4"
            >
                <div class="icon-num d-flex justify-content-evenly">
                    <img
                        class="diningTable-manage-icon align-self-center"
                        src={HashtagImg}
                        alt="hashtag-icon"
                    />
                    <div>{props.info.id}</div>
                </div>
                <div class="icon-num d-flex justify-content-evenly">
                    <img
                        class="diningTable-manage-icon align-self-center"
                        src={SeatsImg}
                        alt="seat-icon"
                    />
                    <div>{props.info.capacity}</div>
                </div>
            </div>
        </div>
    );
}

function ReservationInfoPart(props) {

    let name = props.name


    const [info, setInfo] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getReq("/reserves/" + name)

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
                    <div class="col-4 h-100 d-flex flex-column" id="reservation-list">
                        <div class="d-flex align-items-center p-2">
                            <div id="manage-rest-reservation-list-header">
                                Reservation List
                            </div>
                            <div class="ms-auto" id="manage-rest-reservation-list-info">
                                All Reservations
                            </div>
                        </div>

                        <div class="diningTable-responsive">
                            <diningTable class="diningTable-responsive diningTable rounded-3 overflow-hidden">
                                <tbody>
                                    {
                                        info.map((reserveInfo) => (
                                            <Reserve info={reserveInfo} />
                                        ))
                                    }
                                </tbody>
                            </diningTable>
                        </div>
                    </div>
            }
        </>
    );

}

function Reserve(props) {

    let info = props.info;

    let classType = "manage-reservation-active"

    if (info.isCancelled == true) {
        classType = "manage-reservation-done"
    }

    return (
        <tr class={classType}>
            <td class="manage-reservation-date">{info.reserveDate}</td>
            <td class="reserver">{info.userName}</td>
            <td class="manage-diningTable-id">
                <a href="#"> Table {info.tableId} </a>
            </td>
        </tr>
    );

}

export default ManagerManagePage;