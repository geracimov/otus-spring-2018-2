import React, {Component, Fragment} from "react";

class Genre extends Component {

    constructor(props) {
        super(props);
        this.state = {isEdit: false};
        this.onDelete = this.onDelete.bind(this);
        this.onEdit = this.onEdit.bind(this);
        this.onCancel = this.onCancel.bind(this);
        this.onSave = this.onSave.bind(this);
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
        this.props.onSave(this.idInput.value, this.nameInput.value);
        this.setState({isEdit: false});
    }

    render() {
        const {id, name} = this.props;
        return (
            <Fragment>
                {
                    this.state.isEdit ?
                        <tr key={id}>
                            <td>
                                <form onSubmit={this.onSave}>
                                    <input type="hidden" name="id" value={id}
                                           ref={idInput => {
                                               this.idInput = idInput
                                           }}/>
                                    <input placeholder="Name" ref={nameInput => this.nameInput = nameInput}
                                           defaultValue={name}/>
                                </form>
                            </td>
                            <td>
                                <button onClick={this.onSave}>Save</button>
                            </td>
                            <td>
                                <button onClick={this.onCancel}>Cancel</button>
                            </td>
                        </tr>
                        :
                        <tr key={id}>
                            <td>{name}</td>
                            <td>
                                <button
                                    onClick={this.onEdit}>Edit
                                </button>
                            </td>
                            <td>
                                <button onClick={this.onDelete}>Delete</button>
                            </td>
                        </tr>
                }
            </Fragment>
        )
    }
}

export default Genre;