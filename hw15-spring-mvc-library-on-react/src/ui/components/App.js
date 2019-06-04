import "../static/App.css"
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Link} from "react-router-dom";
import Home from "./Home";
import Authors from "./author/Authors";
import Books from "./book/Books";
import Genres from "./genre/Genres";
import Reviews from "./Reviews";

class App extends Component {

    static  baseUrl(){
        return 'http://127.0.0.1:8080/api/';
    }

    render() {
        return (
            <Router>
                <div className="App">
                    <ul className="menu">
                        <li className="error"><Link to="/">Home</Link></li>
                        <li><Link to="/authors">Authors</Link></li>
                        <li><Link to="/books">Books</Link></li>
                        <li><Link to="/genres">Genres</Link></li>
                        <li><Link to="/reviews">Reviews</Link></li>
                    </ul><hr/>
                    <Route exact path="/" component={Home}/>
                    <Route path="/authors" component={Authors}/>
                    <Route path="/books" component={Books}/>
                    <Route path="/genres" component={Genres}/>
                    <Route path="/reviews" component={Reviews}/>
                </div>
            </Router>
        );
    }
}

export default App;
