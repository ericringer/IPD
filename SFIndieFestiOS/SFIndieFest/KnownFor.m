//
//  KnownFor.m
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "KnownFor.h"

@implementation KnownFor
@synthesize title,url;

//Init object with NSDictionary
- (id)initWithJSON:(NSDictionary *)json
{
    self = [super init];
    if (self) {
        [self assignValues:json];
    }
    return self;
}

//Populate title and url from NSDictionary
-(void)assignValues:(NSDictionary *)json{
    title = [json objectForKey:@"title"];
    url = [json objectForKey:@"url"];
}
@end
