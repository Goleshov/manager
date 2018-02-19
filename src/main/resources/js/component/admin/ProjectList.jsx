import React, {Component} from 'react'
import Project from "./Project.jsx"
import NewProject from "./NewProject.jsx"

export default class ProjectList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {projects: []}
        this.loadData = this.loadData.bind(this)
        this.onChange = this.loadData.bind(this)
    }


    componentDidMount() {

        this.loadData();
    }

    onChange() {

        this.loadData();
    }


    loadData() {
        var self = this
        fetch('http://localhost:8080/projects', {
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
                        self.setState({projects: data});

                    })

                }
            )
    }

    render() {
        const projectList = this.state.projects.map(project =>
            <li key={project.id}>< Project project={project} role={this.props.role} /></li>)
        var newProject
        if (this.props.role == "ROLE_ADMIN") {
            newProject = <div align="right" class="col-sm6">
                <h3>New Project</h3>
                <NewProject onChange={this.onChange}/>
            </div>
        }

        return (
            <div class="container">
                <div align="left" class="col-sm-6">
                    <ul>
                        {projectList}
                    </ul>
                </div>
                {newProject}
            </div>

        )
    }
}
