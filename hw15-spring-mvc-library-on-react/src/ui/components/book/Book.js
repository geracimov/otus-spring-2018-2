import React, {Component, Fragment} from "react";

import Select from "react-dropdown-select";

class Book extends Component {


    constructor(props) {
        super(props);
        this.state = {isEdit: false};
        this.onDelete = this.onDelete.bind(this);
        this.onEdit = this.onEdit.bind(this);
        this.onCancel = this.onCancel.bind(this);
        this.onSave = this.onSave.bind(this);
        this.handleChangeAuthors = this.handleChangeAuthors.bind(this);
        this.handleChangeGenres = this.handleChangeGenres.bind(this);
    }

    onDelete() {
        const {onDelete, id} = this.props;
        onDelete(id);
    }

    onEdit() {
        this.setState({isEdit: true});
    }

    onCancel() {
        this.setState({isEdit: false});
    }

    onSave(event) {
        event.preventDefault();
        this.props.onSave(
            this.idInput.value,
            this.nameInput.value,
            this.yearInput.value,
            this.pageInput.value,
            this.isbnInput.value,
            this.state.selectedAuthors,
            this.state.selectedGenres
        )
        ;
        this.setState({isEdit: false});
    }

    handleChangeAuthors(selectedAuthors) {
        this.setState({selectedAuthors});
    };

    handleChangeGenres(selectedGenres) {
        this.setState({selectedGenres});
    };

    renderEditRow(id, book, authors, genres) {
        return (
            <tr key={id}>
                <td colSpan='6'>
                    <form onSubmit={this.onSave}>
                        <input type="hidden" name="id" value={id}
                               ref={idInput => {
                                   this.idInput = idInput
                               }}/>
                        <input placeholder="Name" ref={nameInput => this.nameInput = nameInput}
                               defaultValue={book.name}/>
                        <input placeholder="Year" ref={yearInput => this.yearInput = yearInput}
                               defaultValue={book.year}/>
                        <input placeholder="Page count" ref={pageInput => this.pageInput = pageInput}
                               defaultValue={book.pageCount}/>
                        <input placeholder="ISBN" ref={isbnInput => this.isbnInput = isbnInput}
                               defaultValue={book.isbn}/>
                        <Select
                            multi={true}
                            labelField={'name'}
                            valueField={'id'}
                            name="authors"
                            options={authors}
                            values={book.authors}
                            onChange={this.handleChangeAuthors}
                        />
                        <Select
                            multi={true}
                            labelField={'name'}
                            valueField={'id'}
                            name="genres"
                            options={genres}
                            values={book.genres}
                            onChange={this.handleChangeGenres}
                        />
                    </form>
                </td>
                <td>
                    <button onClick={this.onSave}>Save</button>
                </td>
                <td>
                    <button onClick={this.onCancel}>Cancel</button>
                </td>
            </tr>
        )
    }

    renderViewRow(id, book) {
        return (<tr key={id}>
            <td>{book.name}</td>
            <td>{book.year}</td>
            <td>{book.pageCount}</td>
            <td>{book.isbn}</td>
            <td>{book.authors ? book.authors.map(author => <div>{author.name}</div>) : null}</td>
            <td>{book.genres ? book.genres.map(genre => <div>{genre.name}</div>) : null}</td>
            <td>
                <button
                    onClick={this.onEdit}>Edit
                </button>
            </td>
            <td>
                <button onClick={this.onDelete}>Delete</button>
            </td>
        </tr>)
    }

    render() {
        const {id, book, authors, genres} = this.props;
        return (
            <Fragment>
                {
                    this.state.isEdit ?
                        this.renderEditRow(id, book, authors, genres)
                        :
                        this.renderViewRow(id, book)
                }
            </Fragment>
        )
    }
}

export default Book;