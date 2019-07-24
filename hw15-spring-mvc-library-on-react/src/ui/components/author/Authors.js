import React, {Component, Fragment} from "react";

import Footer from '../Footer';
import Author from '../author/Author';
import AuthorAdd from '../author/AuthorAdd'
import App from "../App";

class Authors extends Component {

    static getUrl() {
        return App.baseUrl() + 'author';
    }

    constructor(props) {
        super(props);
        this.state = {authors: []};

        this.onDelete = this.onDelete.bind(this);
        this.onAdd = this.onAdd.bind(this);
        this.getAuthors = this.getAuthors.bind(this);
        this.onSave = this.onSave.bind(this);
    }

    componentDidMount() {
        this.getAuthors();
    }

    getAuthors() {
        fetch(Authors.getUrl(), {
            method: 'get'
        })
            .then(response =>
                response.json()
            )
            .then(authors => {
                    this.setState({authors: authors});
                }
            )
            .catch(console.log.bind(console));
    }

    onSave(id, name, birth) {
        fetch(Authors.getUrl(), {
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: id, name: name, birth: birth})
        }).then(() => {
                let updatedAuthors = this.getAuthors().map(author => {
                        if (author.id === id) {
                            author.name = name;
                            author.birth = birth;
                        }
                    }
                );

                this.setState({authors: updatedAuthors})
            }
        ).catch(console.log.bind(console));
    }

    onDelete(id) {
        fetch(Authors.getUrl() + '/' + id, {
            method: 'delete'
        }).then(() => {
            const filteredAuthors = this.state.authors.filter(author => author.id !== id);
            this.setState({authors: filteredAuthors})
        }).catch(console.log.bind(console));
    }


    onAdd(name, birth) {
        fetch(Authors.getUrl(), {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: name, birth: birth})
        }).then(response => {
            return response.json();
        }).then(author => {
            const authors = this.state.authors;
            authors.push({
                id: author.id,
                name: author.name,
                birth: author.birth
            });
            this.setState({authors});
        }).catch(() => {
            console.log.bind(console);
        });
    }

    render() {
        return (
            <Fragment>

                <table>
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Birth</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.authors.map(
                            author => {
                                return (
                                    <Author
                                        key={author.id}
                                        {...author}
                                        onDelete={this.onDelete}
                                        onSave={this.onSave}
                                    />
                                )
                            }
                        )
                    }
                    </tbody>
                </table>
                <AuthorAdd
                    onAdd={this.onAdd}
                />
                <Footer/>
            </Fragment>
        )
    }
}

export default Authors;