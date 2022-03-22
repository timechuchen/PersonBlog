import axios from '@/utils/request'

export function login(admin) {
    return axios({
        url: '/login',
        method: 'POST',
        data: {
            ...admin
        }
    })
}

export function logout() {
    return axios({
        url: '/logout',
        method: 'POST',
    })
}