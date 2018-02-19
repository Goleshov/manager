import React, {Component} from 'react'
import User from "./User.jsx"
import Comment from "./Commnet.jsx"
import NewComment from "./NewComment.jsx";

export default class ProjectList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {task: {comments: [],}, status: ''}
        this.loadData = this.loadData.bind(this)
        this.onChange = this.loadData.bind(this)
        this.changeStatus = this.changeStatus.bind(this)
        this.updateStatus = this.updateStatus.bind(this)
    }


    componentDidMount() {

        this.loadData();
    }

    onChange() {

        this.loadData();
    }

    changeStatus(event) {

        this.setState({status: event.target.value})
    }

    updateStatus() {
        var self = this
        fetch('http://localhost:8080/tasks/' + self.state.task.id + '/' + self.state.status, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            credentials: 'same-origin'
        }).then(
            function () {
                self.loadData();
            }
        )


    }


    loadData() {
        var index = window.location.href.lastIndexOf("/");
        var self = this;

        fetch('http://localhost:8080/task' + window.location.href.slice(index), {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            credentials: 'same-origin'
        })
            .then(
                function (response) {
                    response.json().then(function (data) {
                        self.setState({task: data});

                    })

                }
            )
    }

    render() {
        var addDeveliper
        if (this.props.role == "ROLE_ADMIN") {
            addDeveliper = <div align="right" class="col-sm6">
                <User taskId={this.state.task.id}/>
            </div>
        }
            const commentList = this.state.task.comments.map(comment =>
                <li key={comment.id}><Comment comment={comment} onChange={this.onChange}/></li>)
            return (
                <div class="container">
                    <div align="left" class="col-sm-6">
                        <h1>{this.state.task.name}</h1>
                        <section>{this.state.task.description}</section>
                        <h2>Status {this.state.task.status}</h2>
                        <select class="selectpicker" onChange={this.changeStatus}>
                            <option>Waiting</option>
                            <option>Implementation</option>
                            <option>Verifying</option>
                            <option>Releasing</option>
                        </select>
                        <button onClick={this.updateStatus} type="submit" class="btn">Change Status</button>

                        <ul>
                            {commentList}
                        </ul>

                    </div>

                    {addDeveliper}
                    <div align="center" class="col-sm_3">
                        <NewComment onChange={this.onChange} taskId={this.state.task.id}/>
                    </div>
                </div>

            )
        }
    }