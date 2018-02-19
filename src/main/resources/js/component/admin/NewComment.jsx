import React from 'react'
import toastr from 'toastr'

export default class NewComment extends React.Component {
    constructor(props) {
        super(props)
        this.state = {text:''}
        this.createComment = this.createComment.bind(this)
        this.changeText = this.changeText.bind(this)

    }

    createComment() {
        var self = this
        if (self.state.text!== '' ) {
            fetch('http://localhost:8080/comments/' + self.props.taskId + '/' + self.state.text, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Cache': 'no-cache'
                },
                body: JSON.stringify({name: this.state.name, description: this.state.description}),
                credentials: 'same-origin'
            })
                .then(
                    function () {
                        self.props.onChange();
                    }
                )
        }else {
            toastr.error('Cannot create empty comment')
        }
    }

    changeText(event) {

        this.setState({text: event.target.value})
    }



    render() {
        return (
            <div>
                <h3>Add comment</h3>
                <textarea onChange={this.changeText}></textarea>
                <button onClick={this.createComment}>post</button>
            </div>
        )
    }
}