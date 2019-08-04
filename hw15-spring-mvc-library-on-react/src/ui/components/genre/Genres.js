import React, {Component, Fragment} from "react";

import Footer from '../Footer';
import Genre from './Genre';
import GenreAdd from './GenreAdd'


class Genres extends Component {

    static getUrl() {
        return 'http://127.0.0.1:8080/genre';
    }

    constructor(props) {
        super(props);
        this.state = {genres: []};

        this.onDelete = this.onDelete.bind(this);
        this.onAdd = this.onAdd.bind(this);
        this.getAuthors = this.getGenres.bind(this);
        this.onSave = this.onSave.bind(this);
    }

    componentDidMount() {
        this.getGenres();
    }

    getGenres() {
        fetch(Genres.getUrl(), {
            method: 'get'
        })
            .then(response =>
                response.json()
            )
            .then(genres => this.setState({genres})
            )
            .catch(console.log.bind(console));
    }

    onSave(id, name) {
        fetch(Genres.getUrl(), {
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: id, name: name})
        }).then(() => {
                let updatedGenres = this.getGenres().map(genre => {
                        if (genre.id === id) {
                            genre.name = name
                        }
                    }
                );

                this.setState({genres: updatedGenres})
            }
        ).catch(console.log.bind(console));
    }

    onDelete(id) {
        fetch(Genres.getUrl() + '/' + id, {
            method: 'delete'/*,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: id})*/
        }).then(() => {
            const filteredGenres = this.state.genres.filter(genre => genre.id !== id);
            this.setState({genres: filteredGenres})
        }).catch(console.log.bind(console));
    }


    onAdd(name) {
        fetch(Genres.getUrl(), {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: name})
        }).then(response => {
            return response.json();
        }).then(genre => {
            const genres = this.state.genres;
            genres.push({
                id: genre.id,
                name: genre.name
            });
            this.setState({genres});
        }).catch(() => {
            console.log.bind(console)
        });
    }

    render() {
        return (
            <Fragment>

                <table>
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.genres.map(
                            genre => {
                                return (
                                    <Genre
                                        key={genre.id}
                                        // name={genre.name}
                                        {...genre}
                                        onDelete={this.onDelete}
                                        onSave={this.onSave}
                                    />
                                )
                            }
                        )
                    }
                    </tbody>
                </table>
                <GenreAdd
                    onAdd={this.onAdd}
                />
                <Footer/>
            </Fragment>
        )
    }
}

export default Genres;