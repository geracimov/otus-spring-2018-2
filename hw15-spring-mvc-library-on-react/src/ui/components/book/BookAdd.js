import React, {Component} from "react";

class BookAdd extends Component {

    constructor(props) {
        super(props);

        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();
        this.props.onAdd(this.nameInput.value, this.yearInput.value, this.pageInput.value, this.isbnInput.value);
        this.nameInput.value = '';
        this.yearInput.value = '';
        this.pageInput.value = '';
        this.isbnInput.value = '';
    }

    render() {
        return (
            <form onSubmit={this.onSubmit}>
                <input placeholder="Name" ref={nameInput => this.nameInput = nameInput}/>
                <input placeholder="Year" ref={yearInput => this.yearInput = yearInput}/>
                <input placeholder="Page count" ref={pageInput => this.pageInput = pageInput}/>
                <input placeholder="ISBN" ref={isbnInput => this.isbnInput = isbnInput}/>
                <button>Add</button>
            </form>
        );

    }
}

export default BookAdd;