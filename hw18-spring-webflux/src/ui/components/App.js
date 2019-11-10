import "../static/App.css"
import React, {Component} from "react";
import {BrowserRouter as Router, Link, Route} from "react-router-dom";
import Home from "./Home";
import Authors from "./author/Authors";
import Books from "./book/Books";
import Genres from "./genre/Genres";

class App extends Component {

    static baseUrl() {
        return 'http://localhost:8080/api/';
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
                    </ul>
                    <hr/>
                    <Route exact path="/" component={Home}/>
                    <Route path="/authors" component={Authors}/>
                    <Route path="/books" component={Books}/>
                    <Route path="/genres" component={Genres}/>
                </div>
            </Router>
        );
    }
}

export default App;
