//
//  Data.m
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "Data.h"

@implementation Data
@synthesize bio,birthDate,imdbEndPoint,imdbUrl,knownFor,name,photo;
- (id)init
{
    self = [super init];
    if (self) {
        knownFor = [[NSArray alloc] init];
    }
    return self;
}

//Init object with NSDictionary
- (id)initWithJSON:(NSDictionary *)json
{
    self = [super init];
    if (self) {
        [self assignValues:json];
    }
    return self;
}

//Populate Data object with values from NSDictionary
-(void)assignValues:(NSDictionary *)json{
    bio = [json objectForKey:@"bio"];
    birthDate = [json objectForKey:@"birthDate"];
    imdbEndPoint = [json objectForKey:@"imdbEndPoint"];
    imdbUrl = [json objectForKey:@"imdbUrl"];
    name = [json objectForKey:@"name"];
    photo = [json objectForKey:@"photo"];
    knownFor = [[NSArray alloc] init];
    
    NSMutableArray * tempArray = [[NSMutableArray alloc] init];
    NSArray * knownForJSON = [json objectForKey:@"knownFor"];
    int count = [knownForJSON count];
    for(int i = 0;i<count;i++){
        [tempArray addObject:[[KnownFor alloc] initWithJSON:[knownForJSON objectAtIndex:i]]];
    }
    knownFor = [tempArray copy];
}
@end
