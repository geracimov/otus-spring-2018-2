import React, {Component, Fragment} from "react";

import Footer from '../Footer';
import Book from './Book';
import BookAdd from './BookAdd';
import Authors from "../author/Authors";
import Genres from "../genre/Genres";
import App from "../App";

class Books extends Component {

    static getUrl() {
        return App.baseUrl() + 'book';
    }

    constructor(props) {
        super(props);
        this.state = {books: []};

        this.onDelete = this.onDelete.bind(this);
        this.onAdd = this.onAdd.bind(this);
        this.getBooks = this.getBooks.bind(this);
        this.onSave = this.onSave.bind(this);
    }

    componentDidMount() {
        this.getBooks();
        this.getAuthors();
        this.getGenres();
    }

    getBooks() {
        fetch(Books.getUrl(), {
            method: 'get'
        })
            .then(response =>
                response.json()
            )
            .then(books => this.setState({books})
            )
            .catch(console.log.bind(console));
    }

    getAuthors() {
        fetch(Authors.getUrl(), {
            method: 'get'
        })
            .then(response =>
                response.json()
            )
            .then(allAuthors => this.setState({allAuthors})
            )
            .catch(console.log.bind(console));
    }

    getGenres() {
        fetch(Genres.getUrl(), {
            method: 'get'
        })
            .then(response =>
                response.json()
            )
            .then(allGenres => this.setState({allGenres})
            )
            .catch(console.log.bind(console));
    }

    onSave(id, name, year, pageCount, isbn, authors, genres) {
        fetch(Books.getUrl(), {
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    id: id,
                    name: name,
                    year: year,
                    pageCount: pageCount,
                    isbn: isbn,
                    authors: authors,
                    genres: genres
                })
        }).then(() => {
                let updatedBooks = this.getBooks().map(book => {
                        if (book.id === id) {
                            book.name = name;
                            book.year = year;
                            book.pageCount = pageCount;
                            book.isbn = isbn;
                            book.authors = authors;
                            book.genres = genres;
                        }
                    }
                );
                this.setState({books: updatedBooks})
            }
        ).catch(console.log.bind(console));
    }

    onDelete(id) {
        fetch(Books.getUrl() + '/' + id, {
            method: 'delete'/*,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: id})*/
        }).then(() => {
            const filteredBooks = this.state.books.filter(book => book.id !== id);
            this.setState({books: filteredBooks})
        }).catch(console.log.bind(console));
    }

    onAdd(name, year, pageCount, isbn) {
        fetch(Books.getUrl(), {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: name, year: year, pageCount: pageCount, isbn: isbn})
        }).then(response => {
            return response.json();
        }).then(book => {
            const books = this.state.books;
            books.push({
                id: book.id,
                name: book.name,
                year: book.year,
                pageCount: book.pageCount,
                isbn: book.isbn
            });
            this.setState({books});
        }).catch(() => {
            console.log.bind(console)
        });
    }

    render() {
        return (
            <Fragment>
                <table className="table-striped">
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Year</td>
                        <td>Page count</td>
                        <td>ISBN</td>
                        <td>Authors</td>
                        <td>Genres</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.books.map(
                            book => {
                                return (
                                    <Book
                                        id={book.id}
                                        book={book}
                                        authors={this.state.allAuthors}
                                        genres={this.state.allGenres}
                                        onDelete={this.onDelete}
                                        onSave={this.onSave}
                                    />
                                )
                            }
                        )
                    }
                    </tbody>
                </table>
                <BookAdd
                    onAdd={this.onAdd}
                />
                <Footer/>
            </Fragment>
        )
    }
}

export default Books;