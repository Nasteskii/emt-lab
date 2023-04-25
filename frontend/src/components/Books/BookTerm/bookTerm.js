import React from "react";
import {Link} from "react-router-dom";

class BookTerm extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td scope={"col"}>{this.props.term.name}</td>
                <td scope={"col"}>{this.props.term.category}</td>
                <td scope={"col"}>{this.props.term.authors.map((term) => {
                    return (
                        <li key={term.id}>{term.name}</li>
                    );
                })}</td>
                <td scope={"col"}>{this.props.term.availableCopies}</td>
                <td scope={"col"} className={"text-right"}>
                    <a title={"Delete"} className={"btn btn-danger"} onClick={() => this.props.onDelete(this.props.term.id)}>
                        Delete
                    </a>
                    {/*<a title={"Take Book"} className={"btn btn-danger"} onClick={() => this.props.onTake(this.props.term.id)}>*/}
                    {/*    Take book*/}
                    {/*</a>*/}
                    <Link className={"btn btn-info ms-2"}
                          onClick={() => this.props.onEdit(this.props.term.id)}
                          to={`/books/edit/${this.props.term.id}`}>
                        Edit
                    </Link>
                    <Link className={"btn btn-info ms-2"}
                          onClick={() => this.props.onTake(this.props.term.id)}
                          to={`/books/get-book/${this.props.term.id}`}>
                        Take Book
                    </Link>
                </td>
            </tr>
        )
    }
}

export default BookTerm;