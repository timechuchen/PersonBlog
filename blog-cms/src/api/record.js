import axios from '@/utils/request'

export function saveRecord(moment) {
    return axios({
        url: '/record',
        method: 'POST',
        data: {
            ...moment
        }
    })
}

export function updateRecord(moment) {
    return axios({
        url: '/record',
        method: 'PUT',
        data: {
            ...moment
        }
    })
}

export function getRecordById(id) {
    return axios({
        url: '/record',
        method: 'GET',
        params: {
            id
        }
    })
}

export function getRecordListByQuery() {
    return axios({
        url: '/records',
        method: 'GET',
    })
}

export function updatePublished(id, published) {
    return axios({
        url: '/published',
        method: 'PUT',
        params: {
            id,
            published
        }
    })
}

export function deleteRecordById(id) {
    return axios({
        url: '/record',
        method: 'DELETE',
        params: {
            id
        }
    })
}