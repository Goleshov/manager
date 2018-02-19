import React, {Component} from 'react'
export default class Comment extends React.Component {
    constructor(props) {
        super(props)
        this.state = {text: '', update: false}
        this.UpdateOn = this.UpdateOn.bind(this)
        this.UpdateText = this.UpdateText.bind(this)
        this.UpdateComment = this.UpdateComment.bind(this)
        this.DeleteComment = this.DeleteComment.bind(this)
    }

    UpdateOn() {
        this.setState({update: !this.state.update})

    }

    UpdateText(event) {
        this.setState({text: event.target.value})


    }

    DeleteComment() {
        var self = this
        fetch('http://localhost:8080/comments/' + self.props.comment.id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            credentials: 'same-origin'
        }).then(
            function () {
                self.props.onChange()
            }
        )


    }


    UpdateComment() {
        var self = this
        fetch('http://localhost:8080/comments/' + self.props.comment.id + '/' + self.state.text, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            credentials: 'same-origin'
        }).then(
            function () {
                self.props.onChange()
                self.setState({update: false})
            }
        )


    }


    render() {
        var text

        if (this.state.update) {
            text = <textarea onChange={this.UpdateText} placeholder={this.props.comment.text}></textarea>
        } else {
            text = <textarea onChange={this.UpdateText} disabled value={this.props.comment.text}></textarea>
        }


        var buttons
        if (this.props.comment.canModify) {
            if (this.state.update) {
                buttons = <div>
                    <button onClick={this.UpdateComment}>Ok</button>
                    <button  onClick={this.DeleteComment}>Del</button>
                </div>
            } else {
                buttons = <div>
                    <button onClick={this.UpdateOn}>Update</button>
                    <button onClick={this.DeleteComment}>Del</button>
                </div>
            }
        }
        return (
            <div>
                <h5>{this.props.comment.authorName} {this.props.comment.authorSurname}</h5>
                {text}
                {buttons}

            </div>

        )
    }
}

