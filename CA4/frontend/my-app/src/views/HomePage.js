import React from 'react';
import "../resources/styles/home_page.css"

export default class HomePage extends React.Component {

    render() {
        return (
            <h1>
                Salam bar shoma
            </h1>
        );
    }

}




// export default class Home extends React.Component {

//     constructor(props) {
//         super(props);
//         this.state = {
//             studentInfo: undefined,
//             test: 1
//         }
//         this.updateStudentInfo = this.updateStudentInfo.bind(this);
//         this.reportCards = this.updateReportCards.bind(this);

//     }

//     updateStudentInfo() {
//         API.get("student/", {headers: authHeader()}).then(
//             jsonData => {
//                 this.setState({studentInfo: jsonData.data});
//         }).catch(error => {
//             if(error.response.status == 401 || error.response.status == 403)
//                 window.location.href = "http://localhost:3000/login"
//             console.log('rid')
//         })
//     }

//     updateReportCards() {
//         API.get("student/reportCards", {headers: authHeader()}).then(
//             jsonData => {
//                 this.setState({reportCards: jsonData.data});
//         }).catch(error => {
//             if(error.response.status == 401 || error.response.status == 403)
//                 window.location.href = "http://localhost:3000/login"
//         })
//     }

//     componentDidMount() {
//         document.title = "Home";
//         this.updateStudentInfo()
//         this.updateReportCards()
//     }

//     render() {
//         return (
//             <div className="main-container">
//                 <Header firstOption={"انتخاب واحد"}
//                         secondOption={"برنامه هفتگی"}
//                         firstRoute={"/courses"}
//                         secondRoute={"/schedule"}/>
//                 <main>
//                 <HomeTopSection/>
//                 <div className="container-fluid text-center">
//                     <div className="main row">
//                     <ProfileInfo studentInfo = {this.state.studentInfo}/>
//                     <ReportCards reportCards = {this.state.reportCards}/>
//                     </div>
//                 </div>
//                 </main>
//                 <Footer/>
//             </div>
//         );
//     }

// }