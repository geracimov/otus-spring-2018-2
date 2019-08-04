import React, {Component} from 'react';
import css from "../static/App.css";

export default class Footer extends Component {

    render() {
        return <div className={css.footer}>
            <p>String Library React app &copy; 2019</p>
        </div>
    };
}