
const request = ( url, params = {}, method = 'GET' ) => {
    let options = {
        method,
        headers: { 'Content-Type': 'application/json' },
    };
    if ( 'GET' === method ) {
        url += '?' + ( new URLSearchParams( params ) ).toString();
    } else {
        options.body = JSON.stringify( params );
    }
    
    return fetch( url, options ).then( response => response.json() );
};

export const getReq = ( url, params ) => request( url, params, 'GET' );
export const postReq = ( url, params ) => request( url, params, 'POST' );

// usage
// getReq( 'https://domain.com/path', { param1: value1, param2: value2 } )
// .then( response => {
//     // Do something with response.
// } );