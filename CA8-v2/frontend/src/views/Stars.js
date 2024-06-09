
import React from 'react';
import StarEmpty from "../resources/images/icons/star_empty.svg";
import StarFilled from "../resources/images/icons/star_filled.svg";



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

export default Stars;