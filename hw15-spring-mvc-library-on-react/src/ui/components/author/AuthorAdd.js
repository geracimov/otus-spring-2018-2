import React, {Component} from "react";

class AuthorAdd extends Component {

    constructor(props) {
        super(props);

        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();
        this.props.onAdd(this.nameInput.value, this.birthInput.value);
        this.nameInput.value = '';
        this.birthInput.value = '';
    }

    render() {
        return (
            <form onSubmit={this.onSubmit}>
                <input placeholder="Name" ref={nameInput => this.nameInput = nameInput}/>
                <input placeholder="Birth" ref={birthInput => this.birthInput = birthInput}/>
                <button>Add</button>
            </form>
        );

    }
}

export default AuthorAdd;