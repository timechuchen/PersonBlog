import axios from '@/utils/request'

export function getCategory(queryInfo) {
    return axios({
        url: '/categories',
        method: 'GET',
    })
}

export function addCategory(form) {
    return axios({
        url: '/category',
        method: 'POST',
        data: {
            ...form
        }
    })
}

export function editCategory(form) {
    return axios({
        url: '/category',
        method: 'PUT',
        data: {
            ...form
        }
    })
}

export function deleteCategoryById(id) {
    return axios({
        url: '/category',
        method: 'DELETE',
        params: {
            id
        }
    })
}