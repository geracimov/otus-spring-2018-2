import React, {Component, Fragment} from "react";

class Author extends Component {

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
        this.props.onSave(this.idInput.value, this.nameInput.value, this.birthInput.value);
        this.setState({isEdit: false});
    }

    renderEditRow(id, name, birth) {
        return (
            <tr key={id}>
                <td>
                    <form onSubmit={this.onSave}>
                        <input type="hidden" name="id" value={id}
                               ref={idInput => {
                                   this.idInput = idInput
                               }}/>
                        <input placeholder="Name" ref={nameInput => this.nameInput = nameInput}
                               defaultValue={name}/>
                        <input placeholder="Birth" ref={birthInput => this.birthInput = birthInput}
                               defaultValue={birth}/>
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

    renderViewRow(id, name, birth) {
        return (<tr key={id}>
            <td>{name}</td>
            <td>{birth}</td>
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
        const {id, name, birth} = this.props;
        return (
            <Fragment>
                {
                    this.state.isEdit ?
                        this.renderEditRow(id, name, birth)
                        :
                        this.renderViewRow(id, name, birth)
                }
            </Fragment>
        )
    }
}

export default Author;