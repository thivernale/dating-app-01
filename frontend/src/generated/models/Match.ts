// @ts-nocheck
/* tslint:disable */
/* eslint-disable */
/**
 * dating_app_01 API
 * dating_app_01 API
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
import type { Profile } from './Profile';
import {
    ProfileFromJSON,
    ProfileFromJSONTyped,
    ProfileToJSON,
    ProfileToJSONTyped,
} from './Profile';

/**
 * 
 * @export
 * @interface Match
 */
export interface Match {
    /**
     * 
     * @type {string}
     * @memberof Match
     */
    id?: string;
    /**
     * 
     * @type {Profile}
     * @memberof Match
     */
    profile?: Profile;
    /**
     * 
     * @type {string}
     * @memberof Match
     */
    conversationId?: string;
}

/**
 * Check if a given object implements the Match interface.
 */
export function instanceOfMatch(value: object): value is Match {
    return true;
}

export function MatchFromJSON(json: any): Match {
    return MatchFromJSONTyped(json, false);
}

export function MatchFromJSONTyped(json: any, ignoreDiscriminator: boolean): Match {
    if (json == null) {
        return json;
    }
    return {
        
        'id': json['id'] == null ? undefined : json['id'],
        'profile': json['profile'] == null ? undefined : ProfileFromJSON(json['profile']),
        'conversationId': json['conversationId'] == null ? undefined : json['conversationId'],
    };
}

export function MatchToJSON(json: any): Match {
    return MatchToJSONTyped(json, false);
}

export function MatchToJSONTyped(value?: Match | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'id': value['id'],
        'profile': ProfileToJSON(value['profile']),
        'conversationId': value['conversationId'],
    };
}

